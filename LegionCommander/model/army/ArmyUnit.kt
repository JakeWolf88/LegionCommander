package com.example.legioncommander.model.army

import com.example.legioncommander.model.unitcards.UnitCard
import com.example.legioncommander.model.upgrades.UpgradeCard
import java.util.UUID

data class ArmyUnit(
    val instanceId: String = UUID.randomUUID().toString(),
    val unit: UnitCard,
    val upgrades: List<UpgradeCard?>
) {
    val totalPoints: Int
        get() = unit.points + upgrades.filterNotNull().sumOf { it.points }
}
