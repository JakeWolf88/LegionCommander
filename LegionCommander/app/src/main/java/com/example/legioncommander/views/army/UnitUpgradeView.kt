package com.example.legioncommander.views.army

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legioncommander.model.unitcards.UnitCard
import com.example.legioncommander.model.upgrades.UpgradeCard
import com.example.legioncommander.model.upgrades.UpgradeCardRepository
import com.example.legioncommander.model.upgrades.UpgradeType
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.views.unitcards.UnitCardItem
import com.example.legioncommander.views.upgrades.UpgradeCardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitUpgradeView(
    unit: UnitCard,
    onClose: () -> Unit,
    onAddUnit: (UnitCard, List<UpgradeCard?>) -> Unit
) {
    // List of selected upgrades, corresponding to unit.upgradeSlots indices
    val selectedUpgrades = remember { mutableStateListOf<UpgradeCard?>(*arrayOfNulls<UpgradeCard>(unit.upgradeSlots.size)) }
    var currentSlotIndexSelecting by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        CenterAlignedTopAppBar(
            title = { Text(text = "UPGRADE ${unit.name.uppercase()}", fontFamily = StarJediFontFamily) },
            navigationIcon = {
                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            },
            actions = {
                TextButton(
                    onClick = { onAddUnit(unit, selectedUpgrades.toList()) },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("ADD", fontWeight = FontWeight.Bold)
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Left Side: Unit Card Preview
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UnitCardItem(unit = unit)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                val totalUpgradePoints = selectedUpgrades.filterNotNull().sumOf { it.points }
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        text = "Total Points: ${unit.points + totalUpgradePoints}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        fontFamily = StarJediFontFamily
                    )
                }
            }

            // Right Side: Upgrade Slots
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "UPGRADE SLOTS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = StarJediFontFamily,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(unit.upgradeSlots) { index, slotType ->
                        val selectedUpgrade = selectedUpgrades[index]
                        
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { currentSlotIndexSelecting = index },
                            colors = CardDefaults.cardColors(
                                containerColor = if (currentSlotIndexSelecting == index) 
                                    MaterialTheme.colorScheme.primaryContainer 
                                else MaterialTheme.colorScheme.surfaceVariant
                            ),
                            border = if (currentSlotIndexSelecting == index) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Thumbnail for selected upgrade
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color.Gray)
                                ) {
                                    if (selectedUpgrade != null) {
                                        Image(
                                            painter = painterResource(id = selectedUpgrade.imageRes),
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Icon(
                                            Icons.Default.Add, 
                                            contentDescription = null, 
                                            modifier = Modifier.align(Alignment.Center), 
                                            tint = Color.LightGray
                                        )
                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = slotType.name.replace("_", " ").uppercase(), 
                                        fontWeight = FontWeight.Bold, 
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = selectedUpgrade?.name ?: "EMPTY",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontFamily = if (selectedUpgrade == null) null else StarJediFontFamily
                                    )
                                }
                                if (selectedUpgrade != null) {
                                    Text(
                                        text = "+${selectedUpgrade.points}", 
                                        color = Color.Yellow, 
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = StarJediFontFamily
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Selection Area for current slot
                currentSlotIndexSelecting?.let { slotIndex ->
                    val slotType = unit.upgradeSlots[slotIndex]
                    val upgrades = UpgradeCardRepository.getUpgradesForUnit(unit, slotType)
                    
                    Text(
                        text = "SELECT ${slotType.name.replace("_", " ")}",
                        fontWeight = FontWeight.Bold,
                        fontFamily = StarJediFontFamily,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    if (upgrades.isEmpty()) {
                        Text("No upgrades available for ${slotType.name.lowercase().replace("_", " ")} yet.", color = Color.Gray)
                    } else {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(bottom = 16.dp, end = 16.dp)
                        ) {
                            item {
                                // None option
                                Card(
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(180.dp)
                                        .padding(4.dp)
                                        .clickable {
                                            selectedUpgrades[slotIndex] = null
                                        },
                                    border = BorderStroke(1.dp, Color.Gray),
                                    colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Text("NONE", fontFamily = StarJediFontFamily, color = Color.White)
                                    }
                                }
                            }
                            items(upgrades) { upgrade ->
                                UpgradeCardItem(
                                    upgrade = upgrade,
                                    isSelected = selectedUpgrades[slotIndex]?.id == upgrade.id,
                                    onClick = {
                                        selectedUpgrades[slotIndex] = upgrade
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
