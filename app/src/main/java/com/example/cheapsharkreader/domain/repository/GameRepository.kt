package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun searchGames(query: String)
    fun getCachedGames(): Flow<List<Game>>
    }