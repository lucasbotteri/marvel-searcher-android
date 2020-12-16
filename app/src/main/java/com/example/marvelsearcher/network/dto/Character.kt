package com.example.marvelsearcher.network.dto

import com.example.marvelsearcher.database.entity.CharacterEntity
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

// TODO this has a hardcoded dependency
fun List<CharacterDTO>.asDatabaseEntity(): List<CharacterEntity> = map { c -> CharacterEntity(c.id, c.name, c.thumbnail?.url + c.thumbnail?.path ) }

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    val id: Long,
    val name: String,
    val thumbnail: ImageDTO ?
)
