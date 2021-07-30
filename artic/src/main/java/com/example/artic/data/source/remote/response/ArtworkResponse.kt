package com.example.artic.data.source.remote.response

import com.example.artic.data.source.local.entity.ArtworkConfigEntity
import com.example.artic.data.source.local.entity.ArtworkCopyrightEntity
import com.example.artic.data.source.local.entity.ArtworkEntity
import com.example.artic.util.iso8601ToLong
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworkPaginationResponse(
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("current_page")
    val currentPage: Int,
)

@Serializable
data class ArtworkResponse(
    val pagination: ArtworkPaginationResponse,
    val data: List<ArtworkDataResponse>,
    val info: ArtworkCopyrightResponse?,
    val config: ArtworkConfigResponse?
)

fun ArtworkResponse.asEntities() = data.map {
    ArtworkEntity(
        id = it.id,
        artLastUpdated = it.lastUpdated?.iso8601ToLong() ?: 0, // Change
        totalPages = pagination.totalPages,
        currentPage = pagination.currentPage,
        apiModel = it.apiModel,
        apiLink = it.apiLink,
        isBoosted = it.isBoosted,
        title = it.title,
        altTitles = it.altTitles ?: emptyList(),
        mainReferenceNumber = it.mainReferenceNumber,
        hasNotBeenViewedMuch = it.hasNotBeenViewedMuch,
        boostRank = it.boostRank,
        dateStart = it.dateStart,
        dateEnd = it.dateEnd,
        dateDisplay = it.dateDisplay,
        dateQualifierTitle = it.dateQualifierTitle,
        dateQualifierId = it.dateQualifierId,
        artistDisplay = it.artistDisplay,
        placeOfOrigin = it.placeOfOrigin,
        dimensions = it.dimensions,
        mediumDisplay = it.mediumDisplay,
        inscriptions = it.inscriptions,
        creditLine = it.creditLine,
        publicationHistory = it.publicationHistory,
        exhibitionHistory = it.exhibitionHistory,
        provenanceText = it.provenanceText,
        publishingVerificationLevel = it.publishingVerificationLevel,
        internalDepartmentId = it.internalDepartmentId,
        fiscalYear = it.fiscalYear,
        isZoomable = it.isZoomable,
        colorfulness = it.colorfulness,
        latlon = it.latlon,
        isOnView = it.isOnView,
        onLoanDisplay = it.onLoanDisplay,
        galleryTitle = it.galleryTitle,
        galleryId = it.galleryId,
        artworkTypeTitle = it.artworkTypeTitle,
        artworkTypeId = it.artworkTypeId,
        departmentId = it.departmentId,
        departmentTitle = it.departmentTitle,
        artistId = it.artistId,
        artistTitle = it.artistTitle,
        altArtistIds = it.altArtistIds ?: emptyList(),
        artistIds = it.artistIds ?: emptyList(),
        artistTitles = it.artistTitles ?: emptyList(),
        imageId = it.imageId,
        altImageIds = it.altImageIds ?: emptyList(),
        documentIds = it.documentIds ?: emptyList(),
        soundIds = it.soundIds ?: emptyList(),
        textIds = it.textIds,
        info = info?.asEntity(),
        config = config?.asEntity()
    )
}

@Serializable
data class ArtworkCopyrightResponse(
    @SerialName("license_text")
    val licenseText: String?,
    @SerialName("license_links")
    val licenseLinks: List<String>?,
    val version: String?
)

fun ArtworkCopyrightResponse.asEntity() = ArtworkCopyrightEntity(licenseText, licenseLinks, version)

@Serializable
data class ArtworkConfigResponse(
    @SerialName("iiif_url")
    val iiifUrl: String?,
    @SerialName("website_url")
    val websiteUrl: String?
)

fun ArtworkConfigResponse.asEntity() = ArtworkConfigEntity(iiifUrl, websiteUrl)

@Serializable
data class ArtworkDataResponse(
    val id: Int,
    @SerialName("api_model") val apiModel: String?,
    @SerialName("api_link") val apiLink: String?,
    @SerialName("is_boosted") val isBoosted: Boolean?,
    val title: String?,
    @SerialName("alt_titles") val altTitles: List<String>?,
    @SerialName("main_reference_number") val mainReferenceNumber: String?,
    @SerialName("has_not_been_viewed_much") val hasNotBeenViewedMuch: Boolean?,
    @SerialName("boost_rank") val boostRank: Float?,
    @SerialName("date_start") val dateStart: Int?,
    @SerialName("date_end") val dateEnd: Int?,
    @SerialName("date_display") val dateDisplay: String?,
    @SerialName("date_qualifier_title") val dateQualifierTitle: String?,
    @SerialName("date_qualifier_id") val dateQualifierId: Int?,
    @SerialName("artist_display") val artistDisplay: String?,
    @SerialName("place_of_origin") val placeOfOrigin: String?,
    val dimensions: String?,
    @SerialName("medium_display") val mediumDisplay: String?,
    val inscriptions: String?,
    @SerialName("credit_line") val creditLine: String?,
    @SerialName("publication_history") val publicationHistory: String?,
    @SerialName("exhibition_history") val exhibitionHistory: String?,
    @SerialName("provenance_text") val provenanceText: String?,
    @SerialName("publishing_verification_level") val publishingVerificationLevel: String?,
    @SerialName("internal_department_id") val internalDepartmentId: Int?,
    @SerialName("fiscal_year") val fiscalYear: Int?,
    @SerialName("is_zoomable") val isZoomable: Boolean?,
    val colorfulness: Float?,
    val latlon: String?,
    @SerialName("is_on_view") val isOnView: Boolean?,
    @SerialName("on_loan_display") val onLoanDisplay: String?,
    @SerialName("gallery_title") val galleryTitle: String?,
    @SerialName("gallery_id") val galleryId: Int?,
    @SerialName("artwork_type_title") val artworkTypeTitle: String?,
    @SerialName("artwork_type_id") val artworkTypeId: Int?,
    @SerialName("department_title") val departmentTitle: String?,
    @SerialName("department_id") val departmentId: String?,
    @SerialName("artist_id") val artistId: Int?,
    @SerialName("artist_title") val artistTitle: String?,
    @SerialName("alt_artist_ids") val altArtistIds: List<Int>?,
    @SerialName("artist_ids") val artistIds: List<Int>?,
    @SerialName("artist_titles") val artistTitles: List<String>?,
    @SerialName("image_id") val imageId: String?,
    @SerialName("alt_image_ids") val altImageIds: List<String>?,
    @SerialName("document_ids") val documentIds: List<String>?,
    @SerialName("sound_ids") val soundIds: List<String>?,
    @SerialName("text_ids") val textIds: List<String>,
    @SerialName("last_updated") val lastUpdated: String?,
    val info: ArtworkCopyrightResponse? = null,
    val config: ArtworkConfigResponse? = null
)
