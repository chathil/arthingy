package com.example.artic.domain.repository

import androidx.paging.PagingData
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.model.AgentModel
import com.example.artic.domain.model.ArtworkModel
import kotlinx.coroutines.flow.Flow

interface ArticRepository {
    fun artworks(articConfig: ArticConfig): Flow<PagingData<ArtworkModel>>
    fun individualAgents(
        articConfig: ArticConfig
    ): Flow<PagingData<AgentModel>>
}
