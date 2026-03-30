package com.example.cheapsharkreader.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val gameId: String,
    val title: String,
    val thumb: String
)