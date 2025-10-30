package com.example.legioncommander.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.legioncommander.data.Faction
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
        DeckButtonItem("Rebels Command Deck", R.drawable.rebel_logo, Faction.REBELS),
        DeckButtonItem("Republic Command Deck", R.drawable.republic_logo, Faction.REPUBLIC),
        DeckButtonItem("CIS Command Deck", R.drawable.cis_logo, Faction.SEPARATISTS),
        DeckButtonItem("Empire Command Deck", R.drawable.empire_logo, Faction.EMPIRE),
        DeckButtonItem("Shadow Collective Command Deck", R.drawable.shadow_collective_logo, Faction.SHADOW_COLLECTIVE)
    )

    Column {
        // Use a Box to center the grid on the screen.
        Text("Create Your Command Deck", Modifier.padding(16.dp), fontSize = 24.sp, fontFamily = StarJediFontFamily, fontWeight = FontWeight.Bold)

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
                // Loop through the new list of data objects
                items(deckButtons) { item ->
                    Button(
                        onClick = {
                            val faction: Faction = item.factionIdentifier
                            val factionName: String = faction.name
                            val route: String = Screen.DeckCreation.createRoute(factionName)
                            navController.navigate(route)
                        },
                        shape = RectangleShape,
                        modifier = Modifier.aspectRatio(1f / 1f)
                    ) {
                        // 3. Use a Column to stack the Image and Text vertically
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = item.imageRes),
                                contentDescription = item.label, // For accessibility
                                modifier = Modifier.size(64.dp), // Adjust size as needed
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.height(8.dp)) // Space between image and text
                            Text(
                                text = item.label,
                                textAlign = TextAlign.Center // Center the text if it wraps
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
