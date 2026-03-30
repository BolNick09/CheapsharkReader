package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Deal
import com.example.cheapsharkreader.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface DealRepository {
    suspend fun loadDeals(gameId: String)
    fun getDeals(gameId: String): Flow<List<Deal>>
}