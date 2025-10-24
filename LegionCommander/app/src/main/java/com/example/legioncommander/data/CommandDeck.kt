package com.example.legioncommander.data

//import androidx.room.Entity
//import androidx.room.PrimaryKey

//@Entity(tableName = "command_decks")
data class CommandDeck(
    //TODO: @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val cardIds: List<String>, // A list of the card IDs in this deck
    val faction: Faction // It's good practice to also save the faction
)