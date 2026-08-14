package com.example.legioncommander.model.unitcards

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UnitCardDao {
    @Query("SELECT * FROM unit_cards")
    fun getAllUnits(): Flow<List<UnitCard>>

    /**
     * Finds units that belong to a specific faction.
     * Uses LIKE because factions are stored as a comma-separated string via TypeConverter.
     */
    @Query("SELECT * FROM unit_cards WHERE factions LIKE '%' || :factionName || '%'")
    fun getUnitsForFaction(factionName: String): Flow<List<UnitCard>>

    @Query("SELECT * FROM unit_cards WHERE id = :unitId LIMIT 1")
    suspend fun getUnitById(unitId: String): UnitCard?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: UnitCard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(units: List<UnitCard>)

    @Delete
    suspend fun deleteUnit(unit: UnitCard)
}
