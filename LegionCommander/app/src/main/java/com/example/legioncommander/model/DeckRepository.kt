package com.example.legioncommander.model

import com.example.legioncommander.model.battlecards.BattleDeck
import com.example.legioncommander.model.commandcards.CommandDeck
import kotlinx.coroutines.flow.Flow

/**
 * Repository that abstracts access to the deck data source.
 * It now takes the DAO as a dependency.
 */
class DeckRepository(private val commandDeckDao: CommandDeckDao) {

    // This property now directly exposes the Flow from the DAO.
    // The UI will collect this Flow to get live updates from the database.
    val allDecks: Flow<List<CommandDeck>> = commandDeckDao.getAllDecks()

    val allCommandDecks: Flow<List<CommandDeck>> = commandDeckDao.getAllDecks()
    val allBattleDecks: Flow<List<BattleDeck>> = commandDeckDao.getAllBattleDecks()

    //Command Decks
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

    //Battle Decks
    suspend fun insertBattleDeck(deck: BattleDeck) {
        commandDeckDao.insertBattleDeck(deck)
    }

    suspend fun getBattleDeckById(id: Int): BattleDeck? {
        return commandDeckDao.getBattleDeckById(id)
    }

    suspend fun deleteBattleDeck(deck: BattleDeck) {
        commandDeckDao.deleteBattleDeck(deck)
    }

}
