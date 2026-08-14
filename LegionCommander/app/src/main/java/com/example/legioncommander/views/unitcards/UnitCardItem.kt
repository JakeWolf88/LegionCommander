package com.example.legioncommander.views.unitcards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legioncommander.R
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.unitcards.UnitCard
import com.example.legioncommander.model.unitcards.UnitRank
import com.example.legioncommander.model.unitcards.UnitType
import com.example.legioncommander.ui.theme.LegionCommanderTheme
import com.example.legioncommander.ui.theme.StarJediFontFamily

@Composable
fun UnitCardItem(
    unit: UnitCard,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
        ) {
            // Header with Rank and Points
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = unit.rank.name.replace("_", " "),
                    color = Color.White,
                    fontSize = 10.sp,
                    fontFamily = StarJediFontFamily
                )
                Text(
                    text = "${unit.points}",
                    color = Color.Yellow,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            // Unit Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = unit.imageRes),
                    contentDescription = unit.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Unit Name and Subtitle
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = unit.name.uppercase(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = StarJediFontFamily,
                    lineHeight = 16.sp
                )
                unit.subtitle?.let {
                    Text(
                        text = it,
                        color = Color.LightGray,
                        fontSize = 10.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Keywords (simplified)
                if (unit.keywords.isNotEmpty()) {
                    Text(
                        text = unit.keywords.joinToString(", "),
                        color = Color.White,
                        fontSize = 10.sp,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewUnitCardItem() {
    val sampleUnit = UnitCard(
        id = "luke-skywalker",
        name = "Luke Skywalker",
        subtitle = "Hero of the Rebellion",
        factions = listOf(Faction.REBELS),
        rank = UnitRank.COMMANDER,
        unitType = UnitType.TROOPER,
        points = 160,
        imageRes = R.drawable.rebel_logo,
        keywords = listOf("Jump 1", "Charge", "Immune: Pierce"),
        isUnique = true
    )
    LegionCommanderTheme {
        UnitCardItem(unit = sampleUnit)
    }
}
