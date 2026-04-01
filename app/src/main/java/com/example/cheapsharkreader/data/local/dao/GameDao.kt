package com.example.cheapsharkreader.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.cheapsharkreader.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    fun getAll(): Flow<List<GameEntity>>

    @Upsert
    suspend fun insertAll(games: List<GameEntity>)

    @Query("DELETE FROM games")
    suspend fun clear()
}