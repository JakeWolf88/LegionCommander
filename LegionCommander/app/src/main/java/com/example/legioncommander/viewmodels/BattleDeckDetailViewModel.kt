package com.example.legioncommander.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.legioncommander.model.AppDatabase
import com.example.legioncommander.model.DeckRepository
import com.example.legioncommander.model.battlecards.BattleDeck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BattleDeckDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeckRepository
    private val _deck = MutableStateFlow<BattleDeck?>(null)
    val deck = _deck.asStateFlow()

    init {
        val dao = AppDatabase.getDatabase(application).commandDeckDao()
        repository = DeckRepository(dao)
    }

    fun loadBattleDeck(deckId: Int) {
        viewModelScope.launch {
            val deckFromDb = withContext(Dispatchers.IO) {
                repository.getBattleDeckById(deckId)
            }
            _deck.value = deckFromDb
        }
    }
}
