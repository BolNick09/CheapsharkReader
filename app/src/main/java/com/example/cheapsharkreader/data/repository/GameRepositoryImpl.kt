package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.data.local.dao.GameDao
import com.example.cheapsharkreader.data.local.entity.GameEntity
import com.example.cheapsharkreader.data.remote.api.CheapSharkApi
import com.example.cheapsharkreader.data.remote.mapper.toDomain
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepositoryImpl(
    private val api: CheapSharkApi,
    private val gameDao: GameDao
) : GameRepository {

    override fun getCachedGames(): Flow<List<Game>> {
        return gameDao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun searchGames(query: String) {

        val result = api.searchGames(query)

        gameDao.clear()

        gameDao.insertAll(
            result.map {
                GameEntity(
                    id = it.gameID,
                    title = it.external,
                    thumb = it.thumb,
                    price = it.cheapest ?: "0",
                    lastUpdated = System.currentTimeMillis()
                )
            }
        )
    }
}