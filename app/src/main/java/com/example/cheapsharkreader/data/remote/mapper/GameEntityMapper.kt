package com.example.cheapsharkreader.data.remote.mapper

import android.R.attr.thumb
import com.example.cheapsharkreader.data.local.entity.GameEntity
import com.example.cheapsharkreader.domain.model.Game

fun GameEntity.toDomain() = Game(
    id = id,
    title = title,
    price = "0",
    image = thumb
)
fun Game.toEntity() = GameEntity(
    id = id,
    title = title,
    thumb = image,
    lastUpdated = System.currentTimeMillis()
)