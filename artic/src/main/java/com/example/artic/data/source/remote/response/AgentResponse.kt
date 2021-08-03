package com.example.artic.data.source.remote.response

import com.example.artic.data.source.local.entity.AgentEntity
import com.example.artic.util.iso8601ToLong
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentResponse(
    val paginationResponse: AgentPaginationResponse,
    val data: List<AgentDataResponse>
)

@Serializable
data class AgentPaginationResponse(
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("current_page")
    val currentPage: Int,
)

@Serializable
data class AgentDataResponse(
    val id: Int,
    val title: String?,
    @SerialName("birth_date")
    val birthDate: Int?,
    @SerialName("birth_place")
    val birthPlace: Int?,
    @SerialName("death_date")
    val deathDate: Int?,
    @SerialName("death_place")
    val deathPlace: Int?,
    val description: String?,
    @SerialName("artworks_ids")
    val artworkIds: List<Int>?,
    @SerialName("last_updated")
    val lastUpdated: String?
)

fun AgentResponse.asEntities() = data.map {
    AgentEntity(
        id = it.id,
        agentLastUpdated = it.lastUpdated?.iso8601ToLong() ?: 0,
        totalPages = paginationResponse.totalPages,
        currentPage = paginationResponse.currentPage,
        title = it.title,
        birthDate = it.birthDate,
        birthPlace = it.birthPlace,
        deathDate = it.deathDate,
        deathPlace = it.deathPlace,
        description = it.description,
        artworkIds = it.artworkIds
    )
}
