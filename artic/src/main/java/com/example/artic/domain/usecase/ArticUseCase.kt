package com.example.artic.domain.usecase

import androidx.paging.PagingData
import com.example.artic.domain.model.ArtworkModel
import kotlinx.coroutines.flow.Flow

interface ArticUseCase {
    fun artworks(): Flow<PagingData<ArtworkModel>>
}
