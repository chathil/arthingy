package com.example.artic.domain.repository

import androidx.paging.PagingData
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.model.AgentModel
import com.example.artic.domain.model.ArtworkModel
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface ArticRepository {
    fun artworks(articConfig: ArticConfig = ArticConfig()): Flow<PagingData<ArtworkModel>>
    fun individualAgents(
        articConfig: ArticConfig = ArticConfig(
            fields = "id,title," +
                "artwork_ids," +
                "birth_date," +
                "birth_place," +
                "death_date," +
                "death_place," +
                "description," +
                "last_updated",
            params = "params=" + Json.encodeToString(
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
}
