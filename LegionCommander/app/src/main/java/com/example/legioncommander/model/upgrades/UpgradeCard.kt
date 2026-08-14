package com.example.legioncommander.model.upgrades

import androidx.annotation.DrawableRes
import com.example.legioncommander.model.commandcards.Faction
import kotlinx.serialization.Serializable

@Serializable
enum class UpgradeType {
    HEAVY_WEAPON,
    PERSONNEL,
    FORCE,
    COMMAND,
    HARDPOINT,
    GEAR,
    GRENADES,
    PROGRAMMING,
    COMMS,
    PILOT,
    TRAINING,
    GENERATOR,
    ARMAMENT,
    CREW,
    ORDNANCE,
    SQUAD_LEADER,
    DOCTRINE,
    CLAN
}

@Serializable
enum class UpgradeUsage {
    PERMANENT,
    FLIPPABLE,
    ONE_TIME_USE
}

@Serializable
data class UpgradeCard(
    val id: String,
    val name: String,
    val type: UpgradeType,
    val points: Int,
    @DrawableRes val imageRes: Int,
    val usage: UpgradeUsage = UpgradeUsage.PERMANENT,
    val factions: List<Faction> = emptyList(),
    val keywords: List<String> = emptyList(),
    val restrictedToUnitIds: List<String> = emptyList(),
    val isUnique: Boolean = false
)
