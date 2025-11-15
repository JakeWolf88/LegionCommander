package com.example.legioncommander.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.legioncommander.model.AppDatabase
import com.example.legioncommander.model.commandcards.CommandDeck
import com.example.legioncommander.model.DeckRepository
import com.example.legioncommander.model.battlecards.BattleDeck
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DecksViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeckRepository

    val allDecks: StateFlow<List<CommandDeck>>

    val allBattleDecks: StateFlow<List<BattleDeck>>

    init {
        val commandDeckDao = AppDatabase.getDatabase(application).commandDeckDao()
        repository = DeckRepository(commandDeckDao)

        // This makes it more efficient and easier to consume in the UI
        allDecks = repository.allDecks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        allBattleDecks = repository.allBattleDecks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    //Command Decks
    fun insertCommandDeck(deck: CommandDeck) = viewModelScope.launch {
        repository.insert(deck)
    }

    fun deleteCommandDeck(deck: CommandDeck) = viewModelScope.launch {
        repository.delete(deck)
    }

    //Battle Decks
    fun deleteBattleDeck(deck: BattleDeck) = viewModelScope.launch {
        repository.deleteBattleDeck(deck)
    }

    fun insertBattleDeck(deck: BattleDeck) = viewModelScope.launch {
        repository.insertBattleDeck(deck)
    }
}
