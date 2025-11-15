package com.example.legioncommander.views.battlecards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.model.battlecards.BattleCard
import com.example.legioncommander.model.battlecards.BattleCardRepository
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.viewmodels.BattleDeckDetailViewModel

@Composable
fun BattleDeckDetailView(
    deckId: Int,
    viewModel: BattleDeckDetailViewModel = viewModel()
) {
    LaunchedEffect(deckId) {
        viewModel.loadBattleDeck(deckId)
    }
    val battleDeck by viewModel.deck.collectAsState()
    var isMatchStarted by remember { mutableStateOf(false) }
    val allCards = BattleCardRepository.getAllCards()
    var shuffledPrimary by remember { mutableStateOf<List<BattleCard>>(emptyList()) }
    var shuffledSecondary by remember { mutableStateOf<List<BattleCard>>(emptyList()) }
    var shuffledAdvantage by remember { mutableStateOf<List<BattleCard>>(emptyList()) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (!isMatchStarted) {
        battleDeck?.let { deck ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = deck.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = StarJediFontFamily
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // --- PRIMARY CARDS ---
                val primaryCards = allCards.filter { it.id in deck.primaryCardIds }
                if (primaryCards.isNotEmpty()) {
                    item { SectionHeader("Primary") }
                    items(primaryCards) { card -> BattleCardRow(card = card) }
                }

                // --- SECONDARY CARDS ---
                val secondaryCards = allCards.filter { it.id in deck.secondaryCardIds }
                if (secondaryCards.isNotEmpty()) {
                    item { SectionHeader("Secondary") }
                    items(secondaryCards) { card -> BattleCardRow(card = card) }
                }

                val advantageCards = allCards.filter { it.id in deck.advantageCardIds }
                if (advantageCards.isNotEmpty()) {
                    item { SectionHeader("Advantage") }
                    items(advantageCards) { card -> BattleCardRow(card = card) }
                }
            }
        } ?: run {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
            Button(
                onClick = {
                    battleDeck?.let { deck ->
                        shuffledPrimary = allCards.filter { it.id in deck.primaryCardIds }.shuffled()
                        shuffledSecondary = allCards.filter { it.id in deck.secondaryCardIds }.shuffled()
                        shuffledAdvantage = allCards.filter { it.id in deck.advantageCardIds }.shuffled()
                        isMatchStarted = true
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = battleDeck != null
            ) {
                Text("Start Match", style = MaterialTheme.typography.titleMedium)
            }

        } else {
            MatchView(
                primaryDeck = shuffledPrimary,
                secondaryDeck = shuffledSecondary,
                advantageDeck = shuffledAdvantage
            )
        }
    }
}

@Composable
fun BattleCardRow(card: BattleCard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), // Add some space between cards
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = card.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) { // Renamed parameter for clarity
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = StarJediFontFamily,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
        )
    }
}