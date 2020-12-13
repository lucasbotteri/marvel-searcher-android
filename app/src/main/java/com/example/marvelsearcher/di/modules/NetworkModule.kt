package com.example.marvelsearcher.di.modules

import com.example.marvelsearcher.network.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule(private val BASE_URL: String) {

    @Singleton
    @Provides
    fun provideMarvelService(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

}