package com.example.legioncommander.model.commandcards

import androidx.annotation.DrawableRes

// Enum to represent the different factions in a type-safe way
//TODO: Update this enum name and fields. Maybe call it DeckType
enum class Faction {
    REPUBLIC,
    SEPARATISTS,
    REBELS,
    EMPIRE,
    SHADOW_COLLECTIVE,
    BATTLE_DECK,
}

// Data class to hold the information for a command card
data class CommandCard(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val pips: Int,
    val factions: List<Faction> = emptyList()
)
