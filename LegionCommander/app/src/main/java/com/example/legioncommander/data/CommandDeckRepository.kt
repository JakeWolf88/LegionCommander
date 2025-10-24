package com.example.legioncommander.data

// This object will hold the list of all created decks while the app is running.
object DeckRepository {

    // Create a private mutable list to hold the decks. This is our "in-memory" database.
    private val _decks = mutableListOf<CommandDeck>()

    // Expose an immutable version of the list for other parts of the app to read safely.
    val decks: List<CommandDeck> = _decks

    /**
     * Saves a new command deck to our list.
     * @param deck The CommandDeck to be saved.
     */
    fun saveDeck(deck: CommandDeck) {
        // Here you could add logic to check for duplicate names if you wanted.
        _decks.add(deck)
        println("Deck '${deck.name}' saved! Total decks are now: ${_decks.size}")
    }

    /**
     * Retrieves all saved decks.
     * @return A list of all CommandDeck objects.
     */
    fun getAllDecks(): List<CommandDeck> {
        return _decks
    }

    /**
     * Retrieves a single deck by its ID.
     * @param id The unique ID of the deck.
     * @return The CommandDeck if found, otherwise null.
     */
    fun getDeckById(id: Int): CommandDeck? {
        return _decks.find { it.id == id }
    }
}