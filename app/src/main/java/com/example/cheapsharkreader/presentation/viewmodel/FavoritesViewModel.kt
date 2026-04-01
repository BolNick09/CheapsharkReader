package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel() {

    val favorites: StateFlow<List<Game>> =
        repository.favorites
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun removeFromFavorites(game: Game) {
        viewModelScope.launch {
            repository.remove(game)
        }
    }
}