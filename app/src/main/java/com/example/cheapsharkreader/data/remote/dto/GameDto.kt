package com.example.cheapsharkreader.data.remote.dto

data class GameDto(
    val gameID: String,
    val steamAppID: String?,
    val cheapest: String,
    val cheapestDealID: String,
    val external: String,
    val thumb: String
)