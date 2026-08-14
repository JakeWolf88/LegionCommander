package com.example.legioncommander.model.unitcards

import com.example.legioncommander.R
import com.example.legioncommander.model.commandcards.Faction
import com.example.legioncommander.model.upgrades.UpgradeType
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

/**
 * Repository for Unit Cards.
 * Contains both the persistent database operations and the static library of cards,
 * consistent with other repositories in the project.
 */
class UnitCardRepository(private val unitCardDao: UnitCardDao) {

    // --- Database Operations ---

    val allUnits: Flow<List<UnitCard>> = unitCardDao.getAllUnits()

    fun getUnitsForFaction(faction: Faction): Flow<List<UnitCard>> = 
        unitCardDao.getUnitsForFaction(faction.name)

    suspend fun insertUnit(unit: UnitCard) {
        unitCardDao.insertUnit(unit)
    }

    suspend fun insertUnits(units: List<UnitCard>) {
        unitCardDao.insertUnits(units)
    }

    /**
     * Imports units from a JSON string and persists them to the database.
     */
    suspend fun importUnitsFromJson(jsonString: String) {
        val json = Json { 
            ignoreUnknownKeys = true 
            coerceInputValues = true
        }
        val units = json.decodeFromString<List<UnitCard>>(jsonString)
        unitCardDao.insertUnits(units)
    }

    // --- Static Card Library (Consistent with other Card Repositories) ---

    companion object {
        private val staticUnitsList: List<UnitCard>
            get() = listOf(
                // SHADOW COLLECTIVE
                // Commanders
                UnitCard(
                    id = "gar-saxon-commander",
                    name = "Gar Saxon",
                    subtitle = "Militant Commander",
                    factions = listOf(Faction.SHADOW_COLLECTIVE),
                    rank = UnitRank.COMMANDER,
                    unitType = UnitType.TROOPER,
                    points = 100,
                    imageRes = R.drawable.unit_sc_gar_saxon,
                    upgradeSlots = listOf(UpgradeType.COMMAND, UpgradeType.TRAINING, UpgradeType.GEAR, UpgradeType.GRENADES),
                    keywords = listOf("Compel", "Indomitable", "Jump 2", "Sharpshooter 2"),
                    isUnique = true
                ),
                UnitCard(
                    id = "black-sun-vigo-commander",
                    name = "Black Sun Vigo",
                    factions = listOf(Faction.SHADOW_COLLECTIVE),
                    rank = UnitRank.COMMANDER,
                    unitType = UnitType.TROOPER,
                    points = 50,
                    imageRes = R.drawable.unit_sc_black_sun_vigo,
                    upgradeSlots = listOf(UpgradeType.COMMAND, UpgradeType.TRAINING, UpgradeType.GEAR),
                    keywords = listOf("Independent: Surges", "Precise 1"),
                    isUnique = false
                ),
                UnitCard(
                    id = "pyke-syndicate-capo-commander",
                    name = "Pyke Syndicate Capo",
                    factions = listOf(Faction.SHADOW_COLLECTIVE),
                    rank = UnitRank.COMMANDER,
                    unitType = UnitType.TROOPER,
                    points = 45,
                    imageRes = R.drawable.unit_sc_pyke_syndicate_capo,
                    upgradeSlots = listOf(UpgradeType.COMMAND, UpgradeType.TRAINING, UpgradeType.GEAR),
                    keywords = listOf("Independent: Dodge 1", "Outmaneuver"),
                    isUnique = false
                ),
                // Operatives
                UnitCard(
                    id = "bossk-operative",
                    name = "Bossk",
                    subtitle = "Trandoshan Terror",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.EMPIRE),
                    rank = UnitRank.OPERATIVE,
                    unitType = UnitType.TROOPER,
                    points = 115,
                    imageRes = R.drawable.unit_sc_bossk,
                    upgradeSlots = listOf(UpgradeType.TRAINING, UpgradeType.GEAR, UpgradeType.GRENADES),
                    keywords = listOf("Expert Climber", "Regeneration 3", "Unimpeded"),
                    isUnique = true
                ),
                UnitCard(
                    id = "cad-bane-operative",
                    name = "Cad Bane",
                    subtitle = "Needs No Introduction",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS),
                    rank = UnitRank.OPERATIVE,
                    unitType = UnitType.TROOPER,
                    points = 120,
                    imageRes = R.drawable.unit_sc_cad_bane,
                    upgradeSlots = listOf(UpgradeType.TRAINING, UpgradeType.GEAR, UpgradeType.COMMS),
                    keywords = listOf("Bounty", "Danger Sense 2", "Steady"),
                    isUnique = true
                ),
                UnitCard(
                    id = "sc_darth-maul-operative",
                    name = "Maul",
                    subtitle = "A Rival",
                    factions = listOf(Faction.SHADOW_COLLECTIVE),
                    rank = UnitRank.OPERATIVE,
                    unitType = UnitType.TROOPER,
                    points = 160,
                    imageRes = R.drawable.unit_sc_maul_a_rival,
                    upgradeSlots = listOf(UpgradeType.FORCE, UpgradeType.FORCE, UpgradeType.COMMAND, UpgradeType.TRAINING),
                    keywords = listOf("Ataru Mastery", "Infiltrate", "Jump 1"),
                    isUnique = true
                ),
                UnitCard(
                    id = "savage-opress-operative",
                    name = "Savage Opress",
                    subtitle = "Maul's Enforcer",
                    factions = listOf(Faction.SHADOW_COLLECTIVE),
                    rank = UnitRank.OPERATIVE,
                    unitType = UnitType.TROOPER,
                    points = 120,
                    imageRes = R.drawable.unit_sc_savage_opress,
                    upgradeSlots = listOf(UpgradeType.FORCE, UpgradeType.TRAINING),
                    keywords = listOf("Charge", "Overpower 1", "Relentless"),
                    isUnique = true
                ),
                // Special Forces
                UnitCard(
                    id = "mandalorian-super-commandos-special_forces",
                    name = "Mandalorian Super Commandos",
                    factions = listOf(Faction.SHADOW_COLLECTIVE),
                    rank = UnitRank.SPECIAL_FORCES,
                    unitType = UnitType.TROOPER,
                    points = 70,
                    imageRes = R.drawable.unit_mandalorian_super_commandos,
                    upgradeSlots = listOf(UpgradeType.HEAVY_WEAPON, UpgradeType.TRAINING, UpgradeType.GEAR, UpgradeType.GRENADES),
                    keywords = listOf("Jump 2", "Precise 1"),
                    isUnique = false
                ),
                // Corps
                UnitCard(
                    id = "black-sun-enforcers-corps",
                    name = "Black Sun Enforcers",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.EMPIRE, Faction.SEPARATISTS),
                    rank = UnitRank.CORPS,
                    unitType = UnitType.TROOPER,
                    points = 48,
                    imageRes = R.drawable.unit_black_sun_enforcers,
                    upgradeSlots = listOf(UpgradeType.HEAVY_WEAPON, UpgradeType.PERSONNEL, UpgradeType.GEAR),
                    keywords = listOf("Dauntless", "Precise 1"),
                    isUnique = false
                ),
                UnitCard(
                    id = "pyke-syndicate-foot-soldiers-corps",
                    name = "Pyke Syndicate Foot Soldiers",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.REPUBLIC),
                    rank = UnitRank.CORPS,
                    unitType = UnitType.TROOPER,
                    points = 40,
                    imageRes = R.drawable.unit_pyke_syndicate_foot_soldiers,
                    upgradeSlots = listOf(UpgradeType.HEAVY_WEAPON, UpgradeType.PERSONNEL, UpgradeType.GEAR),
                    keywords = listOf("Danger Sense 1", "Outmaneuver"),
                    isUnique = false
                ),
                UnitCard(
                    id = "weequay-pirates-corps",
                    name = "Weequay Pirates",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.REPUBLIC, Faction.REBELS, Faction.EMPIRE),
                    rank = UnitRank.CORPS,
                    unitType = UnitType.TROOPER,
                    points = 40,
                    imageRes = R.drawable.unit_weequay_pirates,
                    upgradeSlots = listOf(UpgradeType.HEAVY_WEAPON, UpgradeType.PERSONNEL, UpgradeType.GEAR),
                    keywords = listOf("Enrage 1"),
                    isUnique = false
                ),
                // Support
                UnitCard(
                    id = "swoop-bike-riders-support",
                    name = "Swoop Bike Riders",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.REPUBLIC),
                    rank = UnitRank.SUPPORT,
                    unitType = UnitType.REPULSOR_VEHICLE,
                    points = 70,
                    imageRes = R.drawable.unit_swoop_bike_riders,
                    upgradeSlots = listOf(UpgradeType.COMMS),
                    keywords = listOf("Fixed: Front", "Speeder 1"),
                    isUnique = false
                ),
                // Heavy
                UnitCard(
                    id = "speeder-truck-heavy",
                    name = "A-A5 Speeder Truck",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.REBELS),
                    rank = UnitRank.HEAVY,
                    unitType = UnitType.GROUND_VEHICLE,
                    points = 75,
                    imageRes = R.drawable.unit_a_a5_speeder_truck_m,
                    upgradeSlots = listOf(UpgradeType.PILOT, UpgradeType.HARDPOINT, UpgradeType.COMMS),
                    keywords = listOf("Armor", "Transport 1"),
                    isUnique = false
                ),
                UnitCard(
                    id = "speeder-tank-heavy",
                    name = "WLO-5 Speeder Tank",
                    factions = listOf(Faction.SHADOW_COLLECTIVE, Faction.SEPARATISTS, Faction.REPUBLIC, Faction.REBELS, Faction.EMPIRE),
                    rank = UnitRank.HEAVY,
                    unitType = UnitType.GROUND_VEHICLE,
                    points = 100,
                    imageRes = R.drawable.unit_wlo_5_speeder_tank,
                    upgradeSlots = listOf(UpgradeType.PILOT, UpgradeType.HARDPOINT, UpgradeType.COMMS),
                    keywords = listOf("Armor", "Arsenal 2"),
                    isUnique = false
                ),
            )

        fun getStaticUnitsForFaction(faction: Faction): List<UnitCard> {
            return staticUnitsList.filter { it.factions.contains(faction) }
        }

        fun getAllStaticUnits(): List<UnitCard> = staticUnitsList
    }
}
