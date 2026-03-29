package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl : FavoritesRepository {

    private val favorites = mutableListOf<Game>()

    override fun getFavorites(): List<Game> = favorites

    override fun add(game: Game) {
        if (favorites.none { it.id == game.id }) {
            favorites.add(game)
        }
    }

    override fun remove(game: Game) {
        favorites.removeAll { it.id == game.id }
    }

    override fun isFavorite(gameId: String): Boolean {
        return favorites.any { it.id == gameId }
    }
}