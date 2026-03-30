package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(
    repository: FavoritesRepository
) : ViewModel() {

    val favorites: StateFlow<List<Game>> =
        repository.favorites
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}