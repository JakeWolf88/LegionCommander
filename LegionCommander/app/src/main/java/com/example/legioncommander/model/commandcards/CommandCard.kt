package com.example.legioncommander.model.commandcards

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

// Enum to represent the different factions in a type-safe way
@Serializable
enum class Faction {
    REPUBLIC,
    SEPARATISTS,
    REBELS,
    EMPIRE,
    SHADOW_COLLECTIVE,
    BATTLE_DECK,
}

@Serializable
enum class CardStatus
{
    NORMAL,
    OBSOLETE,
    UNRELEASED,
}

// Data class to hold the information for a command card
@Serializable
data class CommandCard(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val pips: Int,
    val factions: List<Faction> = emptyList(),
    val cardStatus: CardStatus = CardStatus.NORMAL,
)
