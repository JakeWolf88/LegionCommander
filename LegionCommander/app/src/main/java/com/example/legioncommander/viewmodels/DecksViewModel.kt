package com.example.legioncommander.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.legioncommander.data.AppDatabase
import com.example.legioncommander.data.CommandDeck
import com.example.legioncommander.data.DeckRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to manage and provide deck data to the UI.
 */
class DecksViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel holds a reference to the repository
    private val repository: DeckRepository

    // Expose the list of all decks as a StateFlow for the UI to collect
    val allDecks: StateFlow<List<CommandDeck>>

    init {
        // Initialize the DAO and Repository
        val commandDeckDao = AppDatabase.getDatabase(application).commandDeckDao()
        repository = DeckRepository(commandDeckDao)

        // Convert the Flow from the repository into a StateFlow
        // This makes it more efficient and easier to consume in the UI
        allDecks = repository.allDecks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    /**
     * Launch a coroutine to insert a new deck into the database.
     */
    fun insert(deck: CommandDeck) = viewModelScope.launch {
        repository.insert(deck)
    }

    fun delete(deck: CommandDeck) = viewModelScope.launch {
        repository.delete(deck)
    }
}
