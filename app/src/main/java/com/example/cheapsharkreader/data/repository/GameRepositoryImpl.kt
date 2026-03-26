package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.data.remote.api.CheapSharkApi
import com.example.cheapsharkreader.data.remote.mapper.toDomain
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.GameRepository

class GameRepositoryImpl(
    private val api: CheapSharkApi
) : GameRepository {

    override suspend fun searchGames(title: String): List<Game> {
        return api.searchGames(title).map { it.toDomain() }
    }
}