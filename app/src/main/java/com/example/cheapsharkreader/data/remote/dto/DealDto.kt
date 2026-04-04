package com.example.cheapsharkreader.data.remote.dto

data class DealDto(
    val storeID: String,
    val dealID: String,
    val price: String?,
    val retailPrice: String?,
    val savings: String?
)