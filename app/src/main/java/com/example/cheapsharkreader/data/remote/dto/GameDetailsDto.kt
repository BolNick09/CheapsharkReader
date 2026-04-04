package com.example.cheapsharkreader.data.remote.dto

data class GameDetailsDto(
    val info: GameInfoDto,
    val deals: List<DealDto>
)

