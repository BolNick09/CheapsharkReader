package com.example.cheapsharkreader.domain.model

data class GameDetails(
    val title: String,
    val image: String,
    val deals: List<Deal>
)