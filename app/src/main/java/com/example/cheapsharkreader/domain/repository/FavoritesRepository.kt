package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Game

interface FavoritesRepository {
    fun getFavorites(): List<Game>
    fun add(game: Game)
    fun remove(game: Game)
    fun isFavorite(gameId: String): Boolean
}