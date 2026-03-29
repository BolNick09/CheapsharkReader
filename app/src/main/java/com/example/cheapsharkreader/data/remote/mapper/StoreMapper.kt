package com.example.cheapsharkreader.data.remote.mapper

import com.example.cheapsharkreader.data.remote.dto.StoreDto
import com.example.cheapsharkreader.domain.model.Store

fun StoreDto.toDomain(): Store {
    return Store(
        id = storeID,
        name = storeName,
        icon = "https://www.cheapshark.com${images.icon}"
    )
}