package com.example.artic.domain.repository

import androidx.paging.PagingData
import com.example.artic.domain.model.ArtworkModel
import kotlinx.coroutines.flow.Flow

interface ArticRepository {
    fun artworks(): Flow<PagingData<ArtworkModel>>
}
