package com.example.cheapsharkreader.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deals")
data class DealEntity(
    @PrimaryKey val dealID: String,
    val gameId: String,
    val storeId: String,
    val price: String,
    val retailPrice: String,
    val savings: String,
    val lastUpdated: Long
)