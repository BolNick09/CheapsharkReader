package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    fun testApi() {
        viewModelScope.launch {
            val games = repository.searchGames("batman")
            games.forEach {
                println("GAME: ${it.title}")
            }
        }
    }
}