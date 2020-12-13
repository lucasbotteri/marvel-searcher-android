package com.example.marvelsearcher.network

import com.example.marvelsearcher.network.dto.CharacterWrapperDTO
import retrofit2.http.GET

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(): CharacterWrapperDTO
}