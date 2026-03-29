package com.example.cheapsharkreader.data.remote.dto

data class StoreDto(
    val storeID: String,
    val storeName: String,
    val images: StoreImagesDto
)

data class StoreImagesDto(
    val banner: String,
    val logo: String,
    val icon: String
)