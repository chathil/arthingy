package com.example.wikipix.domain.usecase

import com.example.wikipix.data.Resource
import kotlinx.coroutines.flow.Flow

interface WikipixUseCase {
    fun loadUrl(title: String): Flow<Resource<String?>>
}
