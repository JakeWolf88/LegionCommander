package com.example.legioncommander.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.legioncommander.model.battlecards.BattleDeck
import com.example.legioncommander.model.commandcards.CommandDeck
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
    @Query("SELECT * FROM command_decks ORDER BY name ASC")
    fun getAllDecks(): Flow<List<CommandDeck>>

    @Delete
    suspend fun deleteDeck(deck: CommandDeck)

    // --- BattleDeck Functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBattleDeck(deck: BattleDeck)

    @Query("SELECT * FROM battle_decks ORDER BY name ASC")
    fun getAllBattleDecks(): Flow<List<BattleDeck>>

    @Query("SELECT * FROM battle_decks WHERE id = :deckId")
    fun getBattleDeckById(deckId: Int): BattleDeck?

    @Delete
    suspend fun deleteBattleDeck(deck: BattleDeck)
}
