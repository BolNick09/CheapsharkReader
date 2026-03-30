package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.repository.GameRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    val games = repository.getCachedGames()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun searchGames(query: String) {
        viewModelScope.launch {
            repository.searchGames(query)
        }
    }
}