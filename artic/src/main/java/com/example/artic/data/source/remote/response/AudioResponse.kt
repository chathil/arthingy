package com.example.artic.data.source.remote.response

import com.example.artic.data.source.local.entity.AudioEntity
import com.example.artic.util.iso8601ToLong
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudioResponse(
    val pagination: AudioPaginationResponse,
    val data: List<AudioDataResponse>
)

@Serializable
data class AudioPaginationResponse(
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("current_page")
    val currentPage: Int
)

@Serializable
data class AudioDataResponse(
    val id: String,
    val content: String,
    @SerialName("artwork_ids") val artworkIds: List<Int>,
    @SerialName("artwork_titles") val artworkTitles: List<String>,
    @SerialName("last_updated") val lastUpdated: String?
)

fun AudioResponse.asEntities() = data.filter {
    it.artworkIds.firstOrNull() != null && it.artworkTitles.firstOrNull() != null
}.map {
    AudioEntity(
        it.artworkIds.first(),
        it.content,
        it.artworkTitles.first(),
        pagination.totalPages,
        pagination.currentPage,
        audioLastUpdated = it.lastUpdated?.iso8601ToLong() ?: 0
    )
}
