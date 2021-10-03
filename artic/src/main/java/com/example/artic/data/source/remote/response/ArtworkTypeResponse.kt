package com.example.artic.data.source.remote.response

import com.example.artic.data.source.local.entity.ArtworkTypeEntity
import com.example.artic.util.iso8601ToLong
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworkTypeResponse(
    val pagination: ArtworkTypePaginationResponse,
    val data: List<ArtworkTypeDataResponse>
)

@Serializable
data class ArtworkTypePaginationResponse(
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("current_page") val currentPage: Int,
)

@Serializable
data class ArtworkTypeDataResponse(
    val id: Int,
    val title: String,
    @SerialName("last_updated") val lastUpdated: String?
)

fun ArtworkTypeResponse.asEntities() = data.map {
    ArtworkTypeEntity(
        id = it.id,
        title = it.title,
        totalPage = pagination.totalPages,
        currentPage = pagination.currentPage,
        typeLastUpdate = it.lastUpdated?.iso8601ToLong() ?: 0
    )
}
