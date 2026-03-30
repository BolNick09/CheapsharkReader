package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.data.local.dao.FavoriteDao
import com.example.cheapsharkreader.data.local.entity.FavoriteEntity
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val dao: FavoriteDao
) : FavoritesRepository {

    override val favorites: Flow<List<Game>> =
        dao.getAll().map { list ->
            list.map {
                Game(
                    id = it.gameId,
                    title = it.title,
                    price = "0",
                    image = it.thumb
                )
            }
        }

    override suspend fun add(game: Game) {
        dao.add(
            FavoriteEntity(
                gameId = game.id,
                title = game.title,
                thumb = game.image
            )
        )
    }

    override suspend fun remove(game: Game) {
        dao.remove(game.id)
    }

    override suspend fun isFavorite(gameId: String): Boolean {
        return dao.isFavorite(gameId)
    }
}