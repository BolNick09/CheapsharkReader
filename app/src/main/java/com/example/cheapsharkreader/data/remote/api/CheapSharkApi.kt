package com.example.cheapsharkreader.data.remote.api

import com.example.cheapsharkreader.data.remote.dto.DealDto
import com.example.cheapsharkreader.data.remote.dto.GameDetailsDto
import com.example.cheapsharkreader.data.remote.dto.GameDto
import com.example.cheapsharkreader.data.remote.dto.StoreDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CheapSharkApi {
    @GET("games")
    suspend fun searchGames(
        @Query("title") title: String
    ): List<GameDto>

    @GET("games")
    suspend fun getGameDetails(
        @Query("id") gameId: String
    ): GameDetailsDto

    @GET("stores")
    suspend fun getStores(): List<StoreDto>

    @GET("deals")
    suspend fun getDeals(
        @Query("gameID") gameId: String
    ): List<DealDto>
}