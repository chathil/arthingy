package com.example.artic.data.source.remote

import com.example.artic.data.source.remote.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun artworks(page: Int, limit: Int) = apiService.artworks(page, limit)
    suspend fun individualAgents(params: String = "", fields: String = "", limit: Int, page: Int) =
        apiService.individualAgents(params, fields, limit, page)
    suspend fun artworkTypes(page: Int, limit: Int) = apiService.artworkTypes(page, limit)
    suspend fun audios(page: Int, limit: Int) = apiService.audios(page, limit)
}
