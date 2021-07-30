package com.example.artic.di

import com.example.artic.data.source.remote.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

private const val BASE_URL = "https://api.artic.edu/api/v1/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideApiService(): ApiService {
        val json = Json { ignoreUnknownKeys = true }
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json"))).build()
        return retrofit.create(ApiService::class.java)
    }
}
