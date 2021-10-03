package com.example.wikipix.domain.repository

import com.example.wikipix.data.Resource
import kotlinx.coroutines.flow.Flow

interface WikipixRepository {
    fun loadUrl(title: String): Flow<Resource<String?>>
    suspend fun loadUrlAsync(title: String): String?
}
