package com.example.legioncommander.model.upgrades

import com.example.legioncommander.R
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.unitcards.UnitCard

object UpgradeCardRepository {
    private val upgradesList = listOf(
        //Armament
        UpgradeCard(
            id = "saxons-galar-90-rifle",
            name = "Saxon's Galar 90-Rifle",
            type = UpgradeType.ARMAMENT,
            points = 10,
            imageRes = R.drawable.upgrade_saxons_galar_90_rifle,
            restrictedToUnitIds = listOf("gar-saxon-commander"),
            isUnique = true
        ),
        UpgradeCard(
            id = "saxons-jetpack-rockets",
            name = "Saxon's Jetpack Rockets",
            type = UpgradeType.ARMAMENT,
            points = 10,
            imageRes = R.drawable.upgrade_saxons_z3x_jetpack_rockets,
            restrictedToUnitIds = listOf("gar-saxon-commander"),
            isUnique = true
        ),
        //Commmand
        //Comms
        UpgradeCard(
            id = "hq-uplink",
            name = "HQ Uplink",
            type = UpgradeType.COMMS,
            points = 10,
            imageRes = R.drawable.recon_mission
        ),
        UpgradeCard(
            id = "comms-jammer",
            name = "Comms Jammer",
            type = UpgradeType.COMMS,
            points = 5,
            imageRes = R.drawable.upgrade_comms_jammer,
            usage = UpgradeUsage.PERMANENT,
        ),
        //Crew
        //Doctrine
        //Force
        //Gear
        UpgradeCard(
            id = "beskar-armor",
            name = "Beskar Armor",
            type = UpgradeType.GEAR,
            points = 15,
            imageRes = R.drawable.we_are_mandalorians,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        UpgradeCard(
            id = "personal-combat-shield",
            name = "Personal Combat Shield",
            type = UpgradeType.GEAR,
            points = 10,
            imageRes = R.drawable.upgrade_personal_combat_shield_leader,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        UpgradeCard(
            id = "whipcord-launcher",
            name = "Whipcord Launcher",
            type = UpgradeType.GEAR,
            points = 5,
            usage = UpgradeUsage.ONE_TIME_USE,
            imageRes = R.drawable.upgrade_whipcord_launcher_mandos,
            factions = listOf(Faction.SHADOW_COLLECTIVE)
        ),
        //Hardpoint
        // Personnel
        UpgradeCard(
            id = "extra-trooper",
            name = "Extra Trooper",
            type = UpgradeType.PERSONNEL,
            points = 10,
            imageRes = R.drawable.roger_roger // Using B1 image as placeholder for trooper
        ),


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
            points = 6,
            imageRes = R.drawable.upgrade_offensive_push,
            usage = UpgradeUsage.FLIPPABLE,
            keywords = listOf("Offensive Push")
        ),
        UpgradeCard(
            id = "on-the-hunt",
            name = "On The Hunt",
            type = UpgradeType.TRAINING,
            points = 6,
            imageRes = R.drawable.upgrade_on_the_hunt,
            usage = UpgradeUsage.PERMANENT,
            keywords = listOf("Hunter")
        ),
        UpgradeCard(
            id = "tenacity",
            name = "Tenacity",
            type = UpgradeType.TRAINING,
            points = 4,
            imageRes = R.drawable.upgrade_tenacity
        ),
        UpgradeCard(
            id = "go-for-broke",
            name = "Go For Broke",
            type = UpgradeType.TRAINING,
            points = 5,
            imageRes = R.drawable.upgrade_go_for_broke
        ),
        UpgradeCard(
            id = "mission-objective",
            name = "Mission Objective",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.FLIPPABLE,
            points = 6,
            imageRes = R.drawable.upgrade_mission_objective
        ),
        UpgradeCard(
            id = "duck-and-cover",
            name = "Duck And Cover",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.PERMANENT,
            points = 2,
            imageRes = R.drawable.upgrade_duck_and_cover
        ),
        UpgradeCard(
            id = "endurance",
            name = "Endurance",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.PERMANENT,
            points = 6,
            imageRes = R.drawable.upgrade_endurance
        ),
        UpgradeCard(
            id = "into-the-fray",
            name = "Into The Fray",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.PERMANENT,
            points = 4,
            imageRes = R.drawable.upgrade_into_the_fray
        ),
        UpgradeCard(
            id = "overwatch",
            name = "Overwatch",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.PERMANENT,
            points = 4,
            imageRes = R.drawable.upgrade_overwatch
        ),
        UpgradeCard(
            id = "protector",
            name = "Protector",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.FLIPPABLE,
            points = 5,
            imageRes = R.drawable.upgrade_protector
        ),
        UpgradeCard(
            id = "seize-the-initiative",
            name = "Seize The Initiative",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.ONE_TIME_USE,
            points = 5,
            imageRes = R.drawable.upgrade_seize_the_initiative
        ),
        UpgradeCard(
            id = "situational-awareness",
            name = "Situational Awareness",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.PERMANENT,
            points = 4,
            imageRes = R.drawable.upgrade_situational_awareness
        ),
        UpgradeCard(
            id = "stuck-in",
            name = "Stuck In",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.PERMANENT,
            points = 8,
            imageRes = R.drawable.upgrade_stuck_in
        ),
        UpgradeCard(
            id = "up-close-and-personal",
            name = "Up Close And Personal",
            type = UpgradeType.TRAINING,
            usage = UpgradeUsage.PERMANENT,
            points = 8,
            imageRes = R.drawable.upgrade_up_close_and_personal
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
