package com.example.cheapsharkreader.data.remote.mapper

import com.example.cheapsharkreader.data.local.entity.GameEntity
import com.example.cheapsharkreader.domain.model.Game

fun GameEntity.toDomain() = Game(
    id = id,
    title = title,
    price = price,
    image = thumb
)
fun Game.toEntity() = GameEntity(
    id = id,
    title = title,
    thumb = image,
    price = price,
    lastUpdated = System.currentTimeMillis()
)