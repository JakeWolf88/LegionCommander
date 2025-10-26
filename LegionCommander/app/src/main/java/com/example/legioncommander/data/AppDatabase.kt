package com.example.legioncommander.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters // Make sure this is imported

// Add this annotation to tell Room about your new Converters class
@TypeConverters(Converters::class)
@Database(entities = [CommandDeck::class], version = 1) // Assuming version 1 for now
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
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
