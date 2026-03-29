package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.model.GameDetails
import com.example.cheapsharkreader.domain.model.Store
import com.example.cheapsharkreader.domain.repository.DealRepository
import com.example.cheapsharkreader.domain.repository.StoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DealsViewModel(
    private val dealRepository: DealRepository,
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow<GameDetails?>(null)
    val state: StateFlow<GameDetails?> = _state

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores

    fun load(gameId: String) {
        viewModelScope.launch {
            _state.value = dealRepository.getGameDetails(gameId)
            _stores.value = storeRepository.getStores()
        }
    }
}