package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    val favorites: Flow<List<Game>>
    suspend fun add(game: Game)
    suspend fun remove(game: Game)
    suspend fun isFavorite(gameId: String): Boolean
}