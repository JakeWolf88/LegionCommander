package com.example.legioncommander.model.upgrades

import com.example.legioncommander.R
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.unitcards.UnitCard

object UpgradeCardRepository {
    private val upgradesList = listOf(
        // Force Upgrades
        UpgradeCard(
            id = "force-push",
            name = "Force Push",
            type = UpgradeType.FORCE,
            points = 10,
            imageRes = R.drawable.push,
            usage = UpgradeUsage.PERMANENT,
            keywords = listOf("Force Push")
        ),
        UpgradeCard(
            id = "force-reflexes",
            name = "Force Reflexes",
            type = UpgradeType.FORCE,
            points = 5,
            imageRes = R.drawable.pulse_scan,
            usage = UpgradeUsage.FLIPPABLE,
            keywords = listOf("Force Reflexes")
        ),
        UpgradeCard(
            id = "battle-meditation",
            name = "Battle Meditation",
            type = UpgradeType.FORCE,
            points = 10,
            imageRes = R.drawable.flow_of_the_force,
            usage = UpgradeUsage.PERMANENT,
            keywords = listOf("Battle Meditation")
        ),
        // Command Upgrades
        UpgradeCard(
            id = "commanding-presence",
            name = "Commanding Presence",
            type = UpgradeType.COMMAND,
            points = 5,
            imageRes = R.drawable.garrison,
            usage = UpgradeUsage.PERMANENT,
            keywords = listOf("Commanding Presence")
        ),
        UpgradeCard(
            id = "improvised-orders",
            name = "Improvised Orders",
            type = UpgradeType.COMMAND,
            points = 5,
            imageRes = R.drawable.ploy,
            usage = UpgradeUsage.FLIPPABLE,
            keywords = listOf("Improvised Orders")
        ),
        UpgradeCard(
            id = "vigilance",
            name = "Vigilance",
            type = UpgradeType.COMMAND,
            points = 12,
            imageRes = R.drawable.constantly_alert
        ),
        // Training Upgrades
        UpgradeCard(
            id = "offensive-push",
            name = "Offensive Push",
            type = UpgradeType.TRAINING,
            points = 4,
            imageRes = R.drawable.aggression,
            usage = UpgradeUsage.ONE_TIME_USE,
            keywords = listOf("Offensive Push")
        ),
        UpgradeCard(
            id = "hunter",
            name = "Hunter",
            type = UpgradeType.TRAINING,
            points = 6,
            imageRes = R.drawable.both_hunter_and_prey,
            usage = UpgradeUsage.PERMANENT,
            keywords = listOf("Hunter")
        ),
        UpgradeCard(
            id = "tenacity",
            name = "Tenacity",
            type = UpgradeType.TRAINING,
            points = 4,
            imageRes = R.drawable.vengeful_strike
        ),
        // Mandalorian / Shadow Collective Specific
        UpgradeCard(
            id = "mando-combat-training",
            name = "Combat Training",
            type = UpgradeType.TRAINING,
            points = 8,
            imageRes = R.drawable.weapons_are_our_religion,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        UpgradeCard(
            id = "beskar-armor",
            name = "Beskar Armor",
            type = UpgradeType.GEAR,
            points = 15,
            imageRes = R.drawable.we_are_mandalorians,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        UpgradeCard(
            id = "whistling-birds",
            name = "Whistling Birds",
            type = UpgradeType.ARMAMENT,
            points = 12,
            imageRes = R.drawable.whistling_birds,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        // Heavy Weapon
        UpgradeCard(
            id = "z6-trooper",
            name = "Z-6 Trooper",
            type = UpgradeType.HEAVY_WEAPON,
            points = 20,
            imageRes = R.drawable.z6_jetpack_rocket
        ),
        // Personnel
        UpgradeCard(
            id = "extra-trooper",
            name = "Extra Trooper",
            type = UpgradeType.PERSONNEL,
            points = 10,
            imageRes = R.drawable.roger_roger // Using B1 image as placeholder for trooper
        ),
        // Comms
        UpgradeCard(
            id = "hq-uplink",
            name = "HQ Uplink",
            type = UpgradeType.COMMS,
            points = 10,
            imageRes = R.drawable.recon_mission
        ),
        // Unit Specific
        UpgradeCard(
            id = "sc-gar-saxon-shield",
            name = "Mandalorian Shield",
            type = UpgradeType.GEAR,
            points = 10,
            imageRes = R.drawable.unit_sc_gar_saxon,
            restrictedToUnitIds = listOf("gar-saxon-commander"),
            isUnique = true
        )
    )

    fun getUpgradesByType(type: UpgradeType): List<UpgradeCard> {
        return upgradesList.filter { it.type == type }
    }

    /**
     * Returns a list of upgrades valid for a specific unit and slot type.
     */
    fun getUpgradesForUnit(unit: UnitCard, slotType: UpgradeType): List<UpgradeCard> {
        return upgradesList.filter { upgrade ->
            upgrade.type == slotType &&
            (upgrade.factions.isEmpty() || unit.factions.any { it in upgrade.factions }) &&
            (upgrade.restrictedToUnitIds.isEmpty() || unit.id in upgrade.restrictedToUnitIds)
        }
    }

    fun getAllUpgrades(): List<UpgradeCard> = upgradesList
}
