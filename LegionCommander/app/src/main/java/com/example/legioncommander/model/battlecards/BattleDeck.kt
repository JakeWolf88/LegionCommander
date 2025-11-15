package com.example.legioncommander.model.battlecards

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "battle_decks")
data class BattleDeck(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val primaryCardIds: List<String>,
    val secondaryCardIds: List<String>,
    val advantageCardIds: List<String>)