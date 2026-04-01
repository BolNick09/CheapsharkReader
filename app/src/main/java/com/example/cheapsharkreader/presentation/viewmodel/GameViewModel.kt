package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import com.example.cheapsharkreader.domain.repository.GameRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class GameViewModel(
    private val repository: GameRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val games = repository.getCachedGames()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val favorites = favoritesRepository.favorites
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun searchGames(query: String) {
        viewModelScope.launch {
            repository.searchGames(query)
        }
    }

    fun toggleFavorite(game: Game) {
        viewModelScope.launch {
            val isFav = favorites.value.any { it.id == game.id }

            if (isFav) {
                favoritesRepository.remove(game)
            } else {
                favoritesRepository.add(game)
            }
        }
    }

    fun isFavorite(game: Game): Boolean {
        return favorites.value.any { it.id == game.id }
    }
}
