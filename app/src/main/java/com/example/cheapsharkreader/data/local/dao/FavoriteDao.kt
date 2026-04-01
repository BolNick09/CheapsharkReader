package com.example.cheapsharkreader.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.cheapsharkreader.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Upsert
    suspend fun add(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE gameId = :gameId")
    suspend fun remove(gameId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE gameId = :gameId)")
    suspend fun isFavorite(gameId: String): Boolean
}