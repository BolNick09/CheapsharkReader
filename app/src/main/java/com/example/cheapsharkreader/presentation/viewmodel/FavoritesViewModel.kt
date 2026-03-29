package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Game>>(emptyList())
    val favorites: StateFlow<List<Game>> = _favorites

    fun load() {
        _favorites.value = repository.getFavorites()
    }

    fun remove(game: Game) {
        repository.remove(game)
        load()
    }
}