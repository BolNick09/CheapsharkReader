package com.example.cheapsharkreader.data.remote.dto

data class GameDetailsDto(
    val info: GameInfoDto,
    val deals: List<DealDto>
)

data class GameInfoDto(
    val title: String,
    val thumb: String
)