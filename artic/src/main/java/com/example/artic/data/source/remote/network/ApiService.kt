package com.example.artic.data.source.remote.network

import com.example.artic.data.source.remote.response.AgentResponse
import com.example.artic.data.source.remote.response.ArtworkResponse
import com.example.artic.data.source.remote.response.ArtworkTypeResponse
import com.example.artic.data.source.remote.response.AudioResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("artworks")
    suspend fun artworks(@Query("page") page: Int, @Query("limit") limit: Int): ArtworkResponse

    @GET("agents/{params}")
    suspend fun individualAgents(
        @Path(value = "params", encoded = true) params: String,
        @Query("fields") fields: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): AgentResponse

    @GET("artwork-types")
    suspend fun artworkTypes(@Query("page") page: Int, @Query("limit") limit: Int): ArtworkTypeResponse

    @GET("sounds")
    suspend fun audios(@Query("page") page: Int, @Query("limit") limit: Int): AudioResponse
}
