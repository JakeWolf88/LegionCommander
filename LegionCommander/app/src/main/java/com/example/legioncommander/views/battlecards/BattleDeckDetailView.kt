package com.example.legioncommander.views.battlecards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.R
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
    var shuffledAdvantage by remember { mutableStateOf<List<BattleCard>>(emptyOf()) }

    var zoomedCard by remember { mutableStateOf<BattleCard?>(null) }

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
                        items(primaryCards) { card -> 
                            BattleCardRow(
                                card = card,
                                onDoubleClick = { zoomedCard = card }
                            ) 
                        }
                    }

                    // --- SECONDARY CARDS ---
                    val secondaryCards = allCards.filter { it.id in deck.secondaryCardIds }
                    if (secondaryCards.isNotEmpty()) {
                        item { SectionHeader("Secondary") }
                        items(secondaryCards) { card -> 
                            BattleCardRow(
                                card = card,
                                onDoubleClick = { zoomedCard = card }
                            ) 
                        }
                    }

                    val advantageCards = allCards.filter { it.id in deck.advantageCardIds }
                    if (advantageCards.isNotEmpty()) {
                        item { SectionHeader("Advantage") }
                        items(advantageCards) { card -> 
                            BattleCardRow(
                                card = card,
                                onDoubleClick = { zoomedCard = card }
                            ) 
                        }
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

        // Zoom Dialog
        zoomedCard?.let { card ->
            Dialog(
                onDismissRequest = { zoomedCard = null },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.9f))
                        .combinedClickable(
                            onClick = { zoomedCard = null },
                            onDoubleClick = { zoomedCard = null }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = card.imageRes),
                        contentDescription = "Zoomed Battle Card",
                        modifier = Modifier.fillMaxSize(0.9f),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BattleCardRow(card: BattleCard, onDoubleClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .combinedClickable(
                onClick = { /* Could toggle selection if needed */ },
                onDoubleClick = onDoubleClick
            ),
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
            // Small indicator that double click is possible
            Text(
                text = "Double tap to zoom",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
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

private fun <T> emptyOf(): List<T> = emptyList()
