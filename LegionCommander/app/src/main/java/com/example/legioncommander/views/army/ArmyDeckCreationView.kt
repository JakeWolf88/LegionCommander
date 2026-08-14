package com.example.legioncommander.views.army

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legioncommander.model.army.ArmyUnit
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.unitcards.UnitCard
import com.example.legioncommander.model.unitcards.UnitCardRepository
import com.example.legioncommander.model.unitcards.UnitRank
import com.example.legioncommander.ui.theme.StarJediFontFamily
import com.example.legioncommander.viewmodel.UnitCardViewModel
import com.example.legioncommander.views.unitcards.UnitCardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArmyDeckCreationView(
    faction: Faction,
    viewModel: UnitCardViewModel = viewModel()
) {
    var selectedUnitForUpgrade by remember { mutableStateOf<UnitCard?>(null) }
    val armyList = remember { mutableStateListOf<ArmyUnit>() }
    
    val totalArmyPoints = armyList.sumOf { it.totalPoints }

    if (selectedUnitForUpgrade != null) {
        UnitUpgradeView(
            unit = selectedUnitForUpgrade!!,
            onClose = { selectedUnitForUpgrade = null },
            onAddUnit = { unit, upgrades ->
                armyList.add(ArmyUnit(unit = unit, upgrades = upgrades))
                selectedUnitForUpgrade = null
            }
        )
    } else {
        // Collect units from the database
        val databaseUnits by viewModel.getUnitsForFaction(faction).collectAsState(initial = emptyList())
        
        // Get static units from the repository
        val staticUnits = remember(faction) { UnitCardRepository.getStaticUnitsForFaction(faction) }
        
        // Combine both sources, ensuring no duplicates by ID, and filter out unique units already in army
        val availableUnits = remember(databaseUnits, staticUnits, armyList.size) {
            (staticUnits + databaseUnits)
                .distinctBy { it.id }
                .filter { unit ->
                    !unit.isUnique || armyList.none { it.unit.id == unit.id }
                }
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${faction.name.uppercase()} ARMY BUILDER",
                                fontFamily = StarJediFontFamily,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "TOTAL: $totalArmyPoints PTS",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (totalArmyPoints > 800) Color.Red else Color.Yellow,
                                fontFamily = StarJediFontFamily
                            )
                        }
                    }
                )
            },
            bottomBar = {
                if (armyList.isNotEmpty()) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        tonalElevation = 12.dp,
                        shadowElevation = 8.dp,
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "YOUR ARMY",
                                fontFamily = StarJediFontFamily,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 8.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                contentPadding = PaddingValues(bottom = 8.dp)
                            ) {
                                items(armyList) { armyUnit ->
                                    AddedUnitItem(
                                        armyUnit = armyUnit,
                                        onDelete = { armyList.remove(armyUnit) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    text = "AVAILABLE UNITS",
                    fontSize = 20.sp,
                    fontFamily = StarJediFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                // The Carousel: One horizontal row for all units
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(availableUnits) { unit ->
                        UnitCardItem(
                            unit = unit,
                            onClick = { selectedUnitForUpgrade = unit }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Placeholder for future JSON Import trigger
                OutlinedButton(
                    onClick = { /* Future: Open file picker */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("IMPORT FROM JSON", fontFamily = StarJediFontFamily)
                }
            }
        }
    }
}

@Composable
fun AddedUnitItem(armyUnit: ArmyUnit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.width(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Gray)
    ) {
        Box {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Unit Image
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Gray)
                ) {
                    androidx.compose.foundation.Image(
                        painter = painterResource(id = armyUnit.unit.imageRes),
                        contentDescription = armyUnit.unit.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = armyUnit.unit.name.uppercase(),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    fontFamily = StarJediFontFamily,
                    color = Color.White
                )

                // Upgrades Icons
                val activeUpgrades = armyUnit.upgrades.filterNotNull()
                if (activeUpgrades.isNotEmpty()) {
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        activeUpgrades.forEach { upgrade ->
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(horizontal = 1.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray)
                            ) {
                                androidx.compose.foundation.Image(
                                    painter = painterResource(id = upgrade.imageRes),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
                
                Text(
                    text = "${armyUnit.totalPoints} PTS",
                    fontSize = 12.sp,
                    color = Color.Yellow,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = StarJediFontFamily
                )
            }
            
            // Delete Button
            Surface(
                onClick = onDelete,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(2.dp)
                    .size(20.dp),
                shape = CircleShape,
                color = Color.Red.copy(alpha = 0.8f)
            ) {
                Icon(
                    Icons.Default.Delete, 
                    contentDescription = "Delete", 
                    tint = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
