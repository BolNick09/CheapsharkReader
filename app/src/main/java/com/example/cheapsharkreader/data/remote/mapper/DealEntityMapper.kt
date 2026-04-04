package com.example.cheapsharkreader.data.remote.mapper

import com.example.cheapsharkreader.data.local.entity.DealEntity
import com.example.cheapsharkreader.domain.model.Deal

fun DealEntity.toDomain() = Deal(
    id = dealID,
    storeId = storeId,
    price = price,
    retailPrice = retailPrice,
    savings = savings
)