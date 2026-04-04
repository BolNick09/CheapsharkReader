package com.example.cheapsharkreader.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.cheapsharkreader.data.local.entity.DealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDao {

    @Query("SELECT * FROM deals WHERE gameId = :gameId")
    fun getDeals(gameId: String): Flow<List<DealEntity>>

    @Upsert
    suspend fun insertAll(deals: List<DealEntity>)

    @Query("DELETE FROM deals WHERE gameId = :gameId")
    suspend fun clearForGame(gameId: String)
}