package com.example.cheapsharkreader.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cheapsharkreader.data.local.dao.DealDao
import com.example.cheapsharkreader.data.local.dao.FavoriteDao
import com.example.cheapsharkreader.data.local.dao.GameDao
import com.example.cheapsharkreader.data.local.entity.DealEntity
import com.example.cheapsharkreader.data.local.entity.FavoriteEntity
import com.example.cheapsharkreader.data.local.entity.GameEntity

@Database(
    entities = [GameEntity::class, DealEntity::class, FavoriteEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
    abstract fun dealDao(): DealDao
    abstract fun favoriteDao(): FavoriteDao
}