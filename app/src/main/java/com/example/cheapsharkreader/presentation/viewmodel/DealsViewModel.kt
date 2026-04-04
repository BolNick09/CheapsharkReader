package com.example.cheapsharkreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapsharkreader.domain.model.Store
import com.example.cheapsharkreader.domain.repository.DealRepository
import com.example.cheapsharkreader.domain.repository.StoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DealsViewModel(
    private val dealRepository: DealRepository,
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _gameId = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val deals = _gameId
        .flatMapLatest { id ->
            dealRepository.getDeals(id)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores

    fun load(gameId: String) {
        _gameId.value = gameId

        viewModelScope.launch {
            dealRepository.loadDeals(gameId)
            _stores.value = storeRepository.getStores()
        }
    }
}