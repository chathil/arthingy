package com.example.artic.domain.usecase

import androidx.paging.PagingData
import com.dropbox.android.external.store4.Store
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.model.AgentModel
import com.example.artic.domain.model.ArtworkModel
import com.example.artic.domain.model.ArtworkTypeModel
import com.example.artic.domain.model.AudioModel
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface ArticUseCase {
    fun pagedArtworks(articConfig: ArticConfig = ArticConfig()): Flow<PagingData<ArtworkModel>>

    fun artworks(articConfig: ArticConfig = ArticConfig()): Store<Int, List<ArtworkModel>>

    fun artworkTypes(articConfig: ArticConfig = ArticConfig()): Store<Int, List<ArtworkTypeModel>>

    fun pagedAgents(
        articConfig: ArticConfig = ArticConfig(
            fields = "id," +
                "title," +
                "artwork_ids," +
                "birth_date," +
                "birth_place," +
                "death_date," +
                "death_place," +
                "description," +
                "last_updated",
            params = "?params=" + Json.encodeToString(
                mapOf(
                    "query" to
                        mapOf(
                            "term" to
                                mapOf("agent_type_id" to 7)
                        )
                )
            )
        )
    ): Flow<PagingData<AgentModel>>

    fun agents(
        articConfig: ArticConfig = ArticConfig()
    ): Store<Int, List<AgentModel>>

    fun audios(articConfig: ArticConfig = ArticConfig()): Store<Int, List<AudioModel>>
}
