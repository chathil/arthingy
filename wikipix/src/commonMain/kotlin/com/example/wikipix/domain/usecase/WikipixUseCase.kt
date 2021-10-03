package com.example.wikipix.domain.usecase

import com.example.wikipix.data.Resource
import kotlinx.coroutines.flow.Flow

interface WikipixUseCase {
    fun loadUrl(title: String): Flow<Resource<String?>>
    suspend fun loadUrlAsync(title: String): String?
    fun observeUrl(title: String, completionHandler: (result: Resource<String?>) -> Unit)
    fun onDestroy()
}
