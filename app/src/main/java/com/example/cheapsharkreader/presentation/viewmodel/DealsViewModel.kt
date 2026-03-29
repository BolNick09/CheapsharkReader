package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.model.GameDetails
import com.example.cheapsharkreader.domain.repository.DealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DealsViewModel(
    private val repository: DealRepository
) : ViewModel() {

    private val _state = MutableStateFlow<GameDetails?>(null)
    val state: StateFlow<GameDetails?> = _state

    fun load(gameId: String) {
        viewModelScope.launch {
            _state.value = repository.getGameDetails(gameId)
        }
    }
}