package com.example.legioncommander.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.legioncommander.data.AppDatabase
import com.example.legioncommander.data.CommandDeck
import com.example.legioncommander.data.DeckRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel to fetch and hold the details for a single deck.
 */
class DeckDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeckRepository

    // A private mutable StateFlow to hold the deck.
    private val _deck = MutableStateFlow<CommandDeck?>(null)
    // A public immutable StateFlow for the UI to observe.
    val deck: StateFlow<CommandDeck?> = _deck

    init {
        // Standard initialization of the repository
        val commandDeckDao = AppDatabase.getDatabase(application).commandDeckDao()
        repository = DeckRepository(commandDeckDao)
    }

    /**
     * Fetches a single deck from the database using its ID and updates the StateFlow.
     * This is a suspend function that will be called from a coroutine.
     */
    fun loadDeck(deckId: Int) {
        viewModelScope.launch {
            _deck.value = repository.getDeckById(deckId)
        }
    }
}
