package com.example.legioncommander.model

import androidx.room.TypeConverter
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.upgrades.UpgradeType

class Converters {
    /**
     * Converts a comma-separated String from the database into a List of Strings.
     */
    @TypeConverter
    fun fromString(value: String?): List<String> {
        return value?.split(",")?.filter { it.isNotEmpty() }?.map { it.trim() } ?: emptyList()
    }

    /**
     * Converts a List of Strings into a single comma-separated String.
     */
    @TypeConverter
    fun fromList(list: List<String>?): String {
        return list?.joinToString(",") ?: ""
    }

    /**
     * Converts a comma-separated String of faction names into a List of Factions.
     */
    @TypeConverter
    fun fromFactionString(value: String?): List<Faction> {
        return value?.split(",")?.filter { it.isNotEmpty() }?.map { Faction.valueOf(it) } ?: emptyList()
    }

    /**
     * Converts a List of Factions into a single comma-separated String of names.
     */
    @TypeConverter
    fun fromFactionList(list: List<Faction>?): String {
        return list?.joinToString(",") { it.name } ?: ""
    }

    /**
     * Converts a comma-separated String of UpgradeType names into a List of UpgradeTypes.
     * Includes robust conversion logic to handle old String formats ("Force" -> "FORCE").
     */
    @TypeConverter
    fun fromUpgradeTypeString(value: String?): List<UpgradeType> {
        return value?.split(",")?.filter { it.isNotEmpty() }?.mapNotNull { 
            try {
                UpgradeType.valueOf(it.trim().uppercase().replace(" ", "_"))
            } catch (e: IllegalArgumentException) {
                null
            }
        } ?: emptyList()
    }

    /**
     * Converts a List of UpgradeTypes into a single comma-separated String of names.
     */
    @TypeConverter
    fun fromUpgradeTypeList(list: List<UpgradeType>?): String {
        return list?.joinToString(",") { it.name } ?: ""
    }
    
    @TypeConverter
    fun fromFaction(faction: Faction): String {
        return faction.name
    }

    @TypeConverter
    fun toFaction(value: String): Faction {
        return Faction.valueOf(value)
    }
}
