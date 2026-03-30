package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.data.local.dao.DealDao
import com.example.cheapsharkreader.data.local.entity.DealEntity
import com.example.cheapsharkreader.data.remote.api.CheapSharkApi
import com.example.cheapsharkreader.data.remote.mapper.toDomain
import com.example.cheapsharkreader.domain.model.Deal
import com.example.cheapsharkreader.domain.model.GameDetails
import com.example.cheapsharkreader.domain.repository.DealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DealRepositoryImpl(
    private val api: CheapSharkApi,
    private val dealDao: DealDao
) : DealRepository {

    override fun getDeals(gameId: String): Flow<List<Deal>> {
        return dealDao.getDeals(gameId).map {
            it.map { d -> d.toDomain() }
        }
    }

    override suspend fun loadDeals(gameId: String) {
        val result = api.getGameDetails(gameId)

        dealDao.clearForGame(gameId)

        dealDao.insertAll(
            result.deals.map {
                DealEntity(
                    dealID = it.dealID,
                    gameId = gameId,
                    storeId = it.storeID,
                    price = it.price ?: "0",
                    retailPrice = it.retailPrice ?: "0",
                    savings = it.savings ?: "0",
                    lastUpdated = System.currentTimeMillis()
                )
            }
        )
    }
}