package com.example.marvelsearcher.network

import com.example.marvelsearcher.network.dto.CharactersWrapperDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharactersByName(@Query("nameStartsWith") nameStartsWith: String): CharactersWrapperDTO

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharactersById(@Path("characterId") characterId: Long): CharactersWrapperDTO
}
