package com.example.cheapsharkreader.domain.repository

import com.example.cheapsharkreader.domain.model.Store

interface StoreRepository {
    suspend fun getStores(): List<Store>
}