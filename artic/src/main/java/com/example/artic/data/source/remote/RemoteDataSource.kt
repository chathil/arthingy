package com.example.artic.data.source.remote

import com.example.artic.data.source.remote.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    //    fun artworks(page: Int) = flow { emit(apiService.artworks(page)) }.flowOn(Dispatchers.IO)
    suspend fun artworks(page: Int) = apiService.artworks(page)
}
