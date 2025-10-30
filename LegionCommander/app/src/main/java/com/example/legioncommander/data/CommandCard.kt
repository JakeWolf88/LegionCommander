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
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val pips: Int,
    val factions: List<Faction> = emptyList()
)
