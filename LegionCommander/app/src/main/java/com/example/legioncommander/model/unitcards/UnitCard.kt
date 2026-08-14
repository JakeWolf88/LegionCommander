package com.example.legioncommander.model.unitcards

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.upgrades.UpgradeType
import kotlinx.serialization.Serializable

@Serializable
enum class UnitRank {
    COMMANDER,
    OPERATIVE,
    CORPS,
    SPECIAL_FORCES,
    SUPPORT,
    HEAVY
}

@Serializable
enum class UnitType {
    TROOPER,
    CREATURE_TROOPER,
    DROID_TROOPER,
    EMPLACEMENT_TROOPER,
    REPARENTED_UNIT,
    GROUND_VEHICLE,
    REPULSOR_VEHICLE
}

/**
 * Represents a Unit Card in Star Wars Legion.
 * Updated to be consistent with CommandCard and BattleCard.
 */
@Serializable
@Entity(tableName = "unit_cards")
data class UnitCard(
    @PrimaryKey val id: String,
    val name: String,
    val subtitle: String? = null,
    val factions: List<Faction>, // Changed to List<Faction> for consistency
    val rank: UnitRank,
    val unitType: UnitType,
    val points: Int,
    @DrawableRes val imageRes: Int, // Non-nullable for consistency
    val upgradeSlots: List<UpgradeType> = emptyList(),
    val keywords: List<String> = emptyList(),
    val isUnique: Boolean = false
)
