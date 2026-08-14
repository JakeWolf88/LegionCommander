package com.example.legioncommander.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.legioncommander.model.battlecards.BattleDeck
import com.example.legioncommander.model.commandcards.CommandDeck
import com.example.legioncommander.model.unitcards.UnitCard
import com.example.legioncommander.model.unitcards.UnitCardDao

@TypeConverters(Converters::class)
@Database(entities = [CommandDeck::class, BattleDeck::class, UnitCard::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun commandDeckDao(): CommandDeckDao
    abstract fun unitCardDao(): UnitCardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "legion_commander_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
