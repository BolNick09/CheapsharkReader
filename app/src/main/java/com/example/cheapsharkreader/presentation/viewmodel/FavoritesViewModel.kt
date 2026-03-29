package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel(
    repository: FavoritesRepository
) : ViewModel() {

    val favorites: StateFlow<List<Game>> = repository.favorites
}