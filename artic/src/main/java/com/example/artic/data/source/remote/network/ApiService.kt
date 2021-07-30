package com.example.artic.data.source.remote.network

import com.example.artic.data.source.remote.response.ArtworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("artworks")
    suspend fun artworks(@Query("page") page: Int): ArtworkResponse
}
