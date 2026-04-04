package com.example.cheapsharkreader.data.remote.mapper

import com.example.cheapsharkreader.data.remote.dto.GameDetailsDto
import com.example.cheapsharkreader.domain.model.GameDetails

fun GameDetailsDto.toDomain(): GameDetails {
    return GameDetails(
        title = info.title,
        image = info.thumb,
        deals = deals.map { it.toDomain() }
    )
}