package com.example.wikipix.di

import co.touchlab.kermit.Kermit
import com.example.wikipix.data.WikipixRepositoryImpl
import com.example.wikipix.data.source.local.LocalDataSource
import com.example.wikipix.data.source.local.WikipixCache
import com.example.wikipix.data.source.remote.RemoteDataSource
import com.example.wikipix.domain.usecase.WikipixInteractor
import com.example.wikipix.domain.usecase.WikipixUseCase
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.coroutines.Dispatchers

class WikipixSdk(
    private val databaseDriverFactory: DatabaseDriverFactory,
    private val log: Kermit
) {
    fun provideUseCase(): WikipixUseCase {
        val database = databaseDriverFactory.createDriver()
        val localDataSource = LocalDataSource(WikipixCache(database), Dispatchers.Default)
        val httpClient = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        log.v("Wikipix Network") { message }
                    }
                }

                level = LogLevel.INFO
            }
        }
        val remoteDataSource = RemoteDataSource(httpClient = httpClient, log = log)
        val repository = WikipixRepositoryImpl(remoteDataSource, localDataSource, log)
        return WikipixInteractor(repository)
    }
}
