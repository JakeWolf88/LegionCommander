package com.example.legioncommander.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.legioncommander.model.AppDatabase
import com.example.legioncommander.model.commandcards.CommandDeck
import com.example.legioncommander.model.DeckRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommandDeckDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeckRepository

    private val _deck = MutableStateFlow<CommandDeck?>(null)

    val deck: StateFlow<CommandDeck?> = _deck

    val usedCardIds = mutableStateListOf<String>()

    init {
        val commandDeckDao = AppDatabase.getDatabase(application).commandDeckDao()
        repository = DeckRepository(commandDeckDao)
    }

    fun loadDeck(deckId: Int) {
        viewModelScope.launch {
            _deck.value = repository.getDeckById(deckId)
        }
    }

    fun toggleCardUsedState(cardId: String) {
        if (usedCardIds.contains(cardId)) {
            usedCardIds.remove(cardId)
        } else {
            usedCardIds.add(cardId)
        }
    }
}
