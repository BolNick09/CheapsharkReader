package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoritesRepositoryImpl : FavoritesRepository {

    private val _favorites = MutableStateFlow<List<Game>>(emptyList())
    override val favorites: StateFlow<List<Game>> = _favorites

    override fun add(game: Game) {
        if (_favorites.value.none { it.id == game.id }) {
            _favorites.value += game
        }
    }

    override fun remove(game: Game) {
        _favorites.value = _favorites.value.filter { it.id != game.id }
    }

    override fun isFavorite(gameId: String): Boolean {
        return _favorites.value.any { it.id == gameId }
    }
}