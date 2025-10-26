package com.example.legioncommander.data

import androidx.room.TypeConverter

class Converters {
    /**
     * Converts a comma-separated String from the database into a List of Strings.
     */
    @TypeConverter
    fun fromString(value: String?): List<String> {
        // If the value from the database is null or empty, return an empty list.
        return value?.split(",")?.map { it.trim() } ?: emptyList()
    }

    /**
     * Converts a List of Strings into a single comma-separated String to be stored in the database.
     */
    @TypeConverter
    fun fromList(list: List<String>?): String {
        // If the list is null, store an empty string.
        return list?.joinToString(",") ?: ""
    }
}
