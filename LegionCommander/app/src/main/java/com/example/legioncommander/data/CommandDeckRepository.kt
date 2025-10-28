package com.example.legioncommander.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that abstracts access to the deck data source.
 * It now takes the DAO as a dependency.
 */
class DeckRepository(private val commandDeckDao: CommandDeckDao) {

    // This property now directly exposes the Flow from the DAO.
    // The UI will collect this Flow to get live updates from the database.
    val allDecks: Flow<List<CommandDeck>> = commandDeckDao.getAllDecks()

    /**
     * Inserts a new deck into the database via the DAO.
     * The 'suspend' keyword indicates this should be called from a coroutine.
     */
    suspend fun insert(deck: CommandDeck) {
        commandDeckDao.insertDeck(deck)
    }

    /**
     * Retrieves a single deck by its ID from the database.
     */
    suspend fun getDeckById(id: Int): CommandDeck? {
        return commandDeckDao.getDeckById(id)
    }

    suspend fun delete(deck: CommandDeck) {
        commandDeckDao.deleteDeck(deck)
    }
}
