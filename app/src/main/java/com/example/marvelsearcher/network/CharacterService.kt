package com.example.marvelsearcher.network

import com.example.marvelsearcher.network.dto.CharacterWrapperDTO
import retrofit2.http.GET
import retrofit2.http.Query



interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharactersByName(@Query("nameStartsWith") nameStartsWith: String): CharacterWrapperDTO
}
