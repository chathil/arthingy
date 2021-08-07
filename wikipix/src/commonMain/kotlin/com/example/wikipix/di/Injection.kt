package com.example.wikipix.di

import com.example.wikipix.data.WikipixRepositoryImpl
import com.example.wikipix.data.source.local.LocalDataSource
import com.example.wikipix.data.source.local.WikipixCache
import com.example.wikipix.data.source.remote.RemoteDataSource
import com.example.wikipix.domain.usecase.WikipixInteractor
import com.example.wikipix.domain.usecase.WikipixUseCase
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json

class WikipixSdk(private val databaseDriverFactory: DatabaseDriverFactory) {
    fun provideUseCase(): WikipixUseCase {
        val database = databaseDriverFactory.createDriver()
        val httpClient = HttpClient {
            install(JsonFeature) {
                val json = Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json = json)
            }
        }
        val localDataSource = LocalDataSource(WikipixCache(database), Dispatchers.Default)
        val remoteDataSource = RemoteDataSource(httpClient = httpClient)
        val repository = WikipixRepositoryImpl(remoteDataSource, localDataSource)
        val interactor = WikipixInteractor(repository)
        return interactor
    }
}
