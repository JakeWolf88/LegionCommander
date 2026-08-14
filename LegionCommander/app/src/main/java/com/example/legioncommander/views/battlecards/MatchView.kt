package com.example.legioncommander.views.battlecards

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.legioncommander.model.battlecards.BattleCard
import com.example.legioncommander.ui.theme.StarJediFontFamily

@Composable
fun DeckPile(
    modifier: Modifier = Modifier,
    cardToShow: BattleCard,
    deckType: String,
    onNextClicked: () -> Unit,
    isNextEnabled: Boolean,
    onZoom: (BattleCard) -> Unit
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = deckType,
            fontFamily = StarJediFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(
            onClick = onNextClicked,
            enabled = isNextEnabled
        ) {
            Text("Next Card")
        }
        FlippableCardView(
            card = cardToShow,
            onDoubleClick = { onZoom(cardToShow) }
        )
    }
}

@Composable
fun MatchView(
    primaryDeck: List<BattleCard>,
    secondaryDeck: List<BattleCard>,
    advantageDeck: List<BattleCard>
) {
    var primaryIndex by remember { mutableStateOf(0) }
    var secondaryIndex by remember { mutableStateOf(0) }
    var advantageIndex by remember { mutableStateOf(0) }
    var zoomedCard by remember { mutableStateOf<BattleCard?>(null) }
    
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLandscape) {
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.Top
            ) {
                if (primaryDeck.isNotEmpty()) {
                    item {
                        DeckPile(
                            cardToShow = primaryDeck[primaryIndex],
                            deckType = "Primary",
                            onNextClicked = { if (primaryIndex < primaryDeck.size - 1) primaryIndex++ },
                            isNextEnabled = primaryIndex < primaryDeck.size - 1,
                            onZoom = { zoomedCard = it }
                        )
                    }
                }
                if (secondaryDeck.isNotEmpty()) {
                    item {
                        DeckPile(
                            cardToShow = secondaryDeck[secondaryIndex],
                            deckType = "Secondary",
                            onNextClicked = { if (secondaryIndex < secondaryDeck.size - 1) secondaryIndex++ },
                            isNextEnabled = secondaryIndex < secondaryDeck.size - 1,
                            onZoom = { zoomedCard = it }
                        )
                    }
                }
                if (advantageDeck.isNotEmpty()) {
                    item {
                        DeckPile(
                            cardToShow = advantageDeck[advantageIndex],
                            deckType = "Advantage",
                            onNextClicked = { if (advantageIndex < advantageDeck.size - 1) advantageIndex++ },
                            isNextEnabled = advantageIndex < advantageDeck.size - 1,
                            onZoom = { zoomedCard = it }
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                if (primaryDeck.isNotEmpty()) {
                    item {
                        DeckPile(
                            cardToShow = primaryDeck[primaryIndex],
                            deckType = "Primary",
                            onNextClicked = { if (primaryIndex < primaryDeck.size - 1) primaryIndex++ },
                            isNextEnabled = primaryIndex < primaryDeck.size - 1,
                            onZoom = { zoomedCard = it }
                        )
                    }
                }
                if (secondaryDeck.isNotEmpty()) {
                    item {
                        DeckPile(
                            cardToShow = secondaryDeck[secondaryIndex],
                            deckType = "Secondary",
                            onNextClicked = { if (secondaryIndex < secondaryDeck.size - 1) secondaryIndex++ },
                            isNextEnabled = secondaryIndex < secondaryDeck.size - 1,
                            onZoom = { zoomedCard = it }
                        )
                    }
                }
                if (advantageDeck.isNotEmpty()) {
                    item {
                        DeckPile(
                            cardToShow = advantageDeck[advantageIndex],
                            deckType = "Advantage",
                            onNextClicked = { if (advantageIndex < advantageDeck.size - 1) advantageIndex++ },
                            isNextEnabled = advantageIndex < advantageDeck.size - 1,
                            onZoom = { zoomedCard = it }
                        )
                    }
                }
            }
        }

        // --- TRUE FULL SCREEN ZOOM DIALOG ---
        zoomedCard?.let { card ->
            Dialog(
                onDismissRequest = { zoomedCard = null },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false,
                    decorFitsSystemWindows = false
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { zoomedCard = null },
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.foundation.Image(
                        painter = painterResource(id = card.imageRes),
                        contentDescription = "Zoomed Card",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}
