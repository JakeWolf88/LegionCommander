package com.example.legioncommander.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CommandDeckDao {

    // Inserts a new deck. If a deck with the same ID already exists, it will be replaced.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeck(deck: CommandDeck)

    // Gets a single deck by its unique ID.
    @Query("SELECT * FROM command_decks WHERE id = :deckId")
    suspend fun getDeckById(deckId: Int): CommandDeck?

    // Gets all decks from the table, ordered by name.
    // Flow is a modern way to observe data changes automatically.
    @Query("SELECT * FROM command_decks ORDER BY name ASC")
    fun getAllDecks(): Flow<List<CommandDeck>>
}
