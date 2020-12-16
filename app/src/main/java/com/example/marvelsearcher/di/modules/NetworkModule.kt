package com.example.marvelsearcher.di.modules

import com.example.marvelsearcher.network.CharacterService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Singleton
    @Provides
    fun provideMarvelService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(@Named("MARVEL_BASE_URL") MARVEL_BASE_URL: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(MARVEL_BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(authenticationInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideAuthenticationInterceptor(@Named("MARVEL_API_KEY") MARVEL_API_KEY: String): Interceptor = Interceptor {
            val original = it.request()
            val originalHttpUrl = original.url()

            val urlWithKey = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", MARVEL_API_KEY)
                .build()

            val requestWithKey = original.newBuilder()
                .addHeader("Referer", "com.example.marvelsearcher")
                .url(urlWithKey)
                .build()

            it.proceed(requestWithKey)
    }


}
