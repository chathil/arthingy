package com.example.artic.domain.repository

import androidx.paging.PagingData
import com.dropbox.android.external.store4.Store
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.model.AgentModel
import com.example.artic.domain.model.ArtworkModel
import com.example.artic.domain.model.ArtworkTypeModel
import com.example.artic.domain.model.AudioModel
import kotlinx.coroutines.flow.Flow

interface ArticRepository {

    fun pagedArtworks(articConfig: ArticConfig): Flow<PagingData<ArtworkModel>>
    fun artworks(articConfig: ArticConfig): Store<Int, List<ArtworkModel>>

    fun pagedArtworkTypes(articConfig: ArticConfig): Flow<PagingData<ArtworkTypeModel>>
    fun artworkTypes(articConfig: ArticConfig): Store<Int, List<ArtworkTypeModel>>

    fun pagedAgents(articConfig: ArticConfig): Flow<PagingData<AgentModel>>
    fun agents(articConfig: ArticConfig): Store<Int, List<AgentModel>>

    fun pagedAudios(articConfig: ArticConfig): Flow<PagingData<AudioModel>>
    fun audios(articConfig: ArticConfig): Store<Int, List<AudioModel>>
}
