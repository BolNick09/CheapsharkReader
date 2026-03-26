package com.example.cheapsharkreader.data.remote.api

import com.example.cheapsharkreader.data.remote.dto.GameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CheapSharkApi {

    @GET("games")
    suspend fun searchGames(
        @Query("title") title: String
    ): List<GameDto>
}