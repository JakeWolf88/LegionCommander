package com.example.legioncommander.data

import androidx.annotation.DrawableRes


// Enum to represent the different factions in a type-safe way
enum class Faction {
    REPUBLIC,
    SEPARATISTS,
    REBELS,
    EMPIRE,
    SHADOW_COLLECTIVE
}

// Data class to hold the information for a command card
data class CommandCard(
    @DrawableRes val picture: Int, // Use @DrawableRes to denote it's a resource ID
    val faction: Faction,
    val name: String,
    val pip: Int
)
