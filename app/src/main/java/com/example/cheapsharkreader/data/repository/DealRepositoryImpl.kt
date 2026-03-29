package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.data.remote.mapper.toDomain
import com.example.cheapsharkreader.data.remote.api.CheapSharkApi
import com.example.cheapsharkreader.domain.model.Deal
import com.example.cheapsharkreader.domain.model.GameDetails
import com.example.cheapsharkreader.domain.repository.DealRepository

class DealRepositoryImpl(
    private val api: CheapSharkApi
): DealRepository {
    override suspend fun getDeals(gameId: String): List<Deal> {
        return api.getDeals(gameId).map { it.toDomain() }
    }
    override suspend fun getGameDetails(gameId: String): GameDetails {
        return api.getGameDetails(gameId).toDomain()
    }
}