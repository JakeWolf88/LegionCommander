package com.example.legioncommander.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.legioncommander.model.battlecards.BattleDeck
import com.example.legioncommander.model.commandcards.CommandDeck

@TypeConverters(Converters::class)
@Database(entities = [CommandDeck::class, BattleDeck::class], version = 2) // Assuming version 1 for now
abstract class AppDatabase : RoomDatabase() {

    abstract fun commandDeckDao(): CommandDeckDao

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
