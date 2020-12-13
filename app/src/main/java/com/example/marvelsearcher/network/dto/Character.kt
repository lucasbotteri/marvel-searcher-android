package com.example.marvelsearcher.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterWrapperDTO(
        val code: Int,
        val status: String,
        val data: CharacterDataContainerDTO
)

@JsonClass(generateAdapter = true)
data class CharacterDataContainerDTO(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterDTO>
)

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    val id: Long,
    val name: String,
    val thumbnail: ImageDTO ?
)