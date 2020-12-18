package com.example.marvelsearcher.network.dto

import com.example.marvelsearcher.config.Constant
import com.example.marvelsearcher.database.entity.CharacterEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersWrapperDTO(
        val code: Int,
        val status: String,
        val data: CharactersDataContainerDTO
)

@JsonClass(generateAdapter = true)
data class CharactersDataContainerDTO(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterDTO>
)

// TODO this has a hardcoded dependency
fun List<CharacterDTO>.asDatabaseEntity(): List<CharacterEntity> = map { c -> c.asDatabaseEntity() }

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ImageDTO
)

// TODO this has a hardcoded dependency
fun CharacterDTO.asDatabaseEntity() = CharacterEntity(id, name, "${thumbnail.path.replace("http", "https")}/${Constant.MARVEL_THUMBNAIL_SIZE}", description )
