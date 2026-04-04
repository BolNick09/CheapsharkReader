package com.example.cheapsharkreader.data.remote.mapper

import com.example.cheapsharkreader.data.remote.dto.GameDto
import com.example.cheapsharkreader.domain.model.Game

fun GameDto.toDomain(): Game {
    return Game(
        id = gameID,
        title = external,
        price = cheapest,
        image = thumb
    )
}