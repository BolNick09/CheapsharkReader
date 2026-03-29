package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Deal
import com.example.cheapsharkreader.domain.model.GameDetails

interface DealRepository {
    suspend fun getDeals(gameId: String): List<Deal>
    suspend fun getGameDetails(gameId: String): GameDetails
}