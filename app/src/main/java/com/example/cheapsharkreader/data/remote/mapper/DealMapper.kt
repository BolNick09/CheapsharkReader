package com.example.cheapsharkreader.data.remote.mapper

import com.example.cheapsharkreader.data.remote.dto.DealDto
import com.example.cheapsharkreader.domain.model.Deal

fun DealDto.toDomain(): Deal {
    return Deal(
        id = dealID,
        storeId = storeID,
        price = price ?: "No data",
        retailPrice = retailPrice ?: "No data",
        savings = savings ?: "No data"
    )
}