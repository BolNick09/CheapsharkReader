package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Game
import kotlinx.coroutines.flow.StateFlow

interface FavoritesRepository {
    val favorites: StateFlow<List<Game>>
    fun add(game: Game)
    fun remove(game: Game)
    fun isFavorite(gameId: String): Boolean
}