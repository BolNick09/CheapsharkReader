package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Game

interface GameRepository {
    suspend fun searchGames(title: String): List<Game>
}