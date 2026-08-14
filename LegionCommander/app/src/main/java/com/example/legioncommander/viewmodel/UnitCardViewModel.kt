package com.example.legioncommander.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.legioncommander.model.AppDatabase
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.unitcards.UnitCard
import com.example.legioncommander.model.unitcards.UnitCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UnitCardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UnitCardRepository

    init {
        val unitCardDao = AppDatabase.getDatabase(application).unitCardDao()
        repository = UnitCardRepository(unitCardDao)
    }

    val allUnits: Flow<List<UnitCard>> = repository.allUnits

    fun getUnitsForFaction(faction: Faction): Flow<List<UnitCard>> = repository.getUnitsForFaction(faction)

    fun insertUnit(unit: UnitCard) = viewModelScope.launch {
        repository.insertUnit(unit)
    }

    fun insertUnits(units: List<UnitCard>) = viewModelScope.launch {
        repository.insertUnits(units)
    }

    fun importUnitsFromJson(jsonString: String) = viewModelScope.launch {
        repository.importUnitsFromJson(jsonString)
    }
}
