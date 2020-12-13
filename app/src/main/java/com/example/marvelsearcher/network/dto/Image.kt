package com.example.marvelsearcher.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDTO(
    val path: String,
    val url: String,
    )