package com.example.legioncommander.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.legioncommander.R
import com.example.legioncommander.Screen
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily

data class DeckButtonItem(
    val label: String,
    @DrawableRes val imageRes: Int,
    val factionIdentifier: Faction
)

@Composable
fun DeckBuilderView(navController: NavController) {

    val deckButtons = listOf(
        DeckButtonItem("Rebels", R.drawable.rebel_logo, Faction.REBELS),
        DeckButtonItem("Republic", R.drawable.republic_logo, Faction.REPUBLIC),
        DeckButtonItem("Separatists", R.drawable.cis_logo, Faction.SEPARATISTS),
        DeckButtonItem("Empire", R.drawable.empire_logo, Faction.EMPIRE),
        DeckButtonItem("Shadow Collective", R.drawable.shadow_collective_logo, Faction.SHADOW_COLLECTIVE),
        DeckButtonItem("Battle Deck", R.drawable.battle_deck_icon, Faction.BATTLE_DECK),
    )

    var showDialog by remember { mutableStateOf(false) }
    var selectedFaction by remember { mutableStateOf<Faction?>(null) }

    if (showDialog && selectedFaction != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Select Deck Type", fontFamily = StarJediFontFamily) },
            text = { Text("Would you like to create a Command Deck or an Army Deck for ${selectedFaction!!.name.lowercase().capitalize()}?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    val factionName: String = selectedFaction!!.name
                    navController.navigate(Screen.DeckCreation.createRoute(factionName))
                }) {
                    Text("Command Deck")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    val factionName: String = selectedFaction!!.name
                    navController.navigate(Screen.ArmyDeckCreation.createRoute(factionName))
                }) {
                    Text("Army Deck")
                }
            }
        )
    }

    Column {
        Text("Create Your Deck", Modifier.padding(16.dp), fontSize = 24.sp, fontFamily = StarJediFontFamily, fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(deckButtons) { item ->
                    Button(
                        onClick = {
                            if (item.factionIdentifier == Faction.BATTLE_DECK) {
                                // Battle Deck might still use a specialized flow or we can fix the route name mismatch
                                // Fixed typo: changed BattledDeckCreation to BattleDeckCreation
                                navController.navigate(Screen.BattleDeckCreation.createRoute("BATTLE_DECK"))
                            } else {
                                selectedFaction = item.factionIdentifier
                                showDialog = true
                            }
                        },
                        shape = RectangleShape,
                        modifier = Modifier.aspectRatio(1f / 1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = item.imageRes),
                                contentDescription = item.label,
                                modifier = Modifier.size(125.dp),
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                fontFamily = StarJediFontFamily,
                                text = item.label,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeckBuilderViewPreview() {
    LegionCommanderTheme {
        DeckBuilderView(navController = rememberNavController())
    }
}
