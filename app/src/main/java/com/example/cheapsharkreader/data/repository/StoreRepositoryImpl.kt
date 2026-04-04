package com.example.cheapsharkreader.data.repository

import com.example.cheapsharkreader.data.remote.api.CheapSharkApi
import com.example.cheapsharkreader.data.remote.mapper.toDomain
import com.example.cheapsharkreader.domain.model.Store
import com.example.cheapsharkreader.domain.repository.StoreRepository

class StoreRepositoryImpl (
    private val api: CheapSharkApi
) : StoreRepository {
    private var cachedStores: List<Store>? = null

    override suspend fun getStores(): List<Store> {
        if (cachedStores != null) return cachedStores!!

        val stores = api.getStores().map { it.toDomain() }
        cachedStores = stores
        return stores
    }
}