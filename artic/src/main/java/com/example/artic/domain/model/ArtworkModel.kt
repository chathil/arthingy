package com.example.artic.domain.model

data class ArtworkModel(
    val id: Int,
    val lastUpdated: Long,
    val totalPages: Int,
    val currentPage: Int,
    val apiModel: String?,
    val apiLink: String?,
    val isBoosted: Boolean?,
    val title: String?,
    val altTitles: List<String>?,
    val mainReferenceNumber: String?,
    val hasNotBeenViewedMuch: Boolean?,
    val boostRank: Float?,
    val dateStart: Int?,
    val dateEnd: Int?,
    val dateDisplay: String?,
    val dateQualifierTitle: String?,
    val dateQualifierId: Int?,
    val artistDisplay: String?,
    val placeOfOrigin: String?,
    val dimensions: String?,
    val mediumDisplay: String?,
    val inscriptions: String?,
    val creditLine: String?,
    val publicationHistory: String?,
    val exhibitionHistory: String?,
    val provenanceText: String?,
    val publishingVerificationLevel: String?,
    val internalDepartmentId: Int?,
    val fiscalYear: Int?,
    val isZoomable: Boolean?,
    val colorfulness: Float?,
    val latlon: String?,
    val isOnView: Boolean?,
    val onLoanDisplay: String?,
    val galleryTitle: String?,
    val galleryId: Int?,
    val artworkTypeTitle: String?,
    val artworkTypeId: Int?,
    val departmentTitle: String?,
    val departmentId: String?,
    val artistId: Int?,
    val artistTitle: String?,
    val altArtistIds: List<Int>?,
    val artistIds: List<Int>?,
    val artistTitles: List<String>?,
    val imageId: String?,
    val altImageIds: List<String>?,
    val documentIds: List<String>?,
    val soundIds: List<String>?,
    val textIds: List<String>,
    val info: ArtworkCopyrightModel?,
    val config: ArtworkConfigModel?
)

data class ArtworkCopyrightModel(
    val licenseText: String?,
    val licenseLinks: List<String>?,
    val version: String?
)

data class ArtworkConfigModel(
    val iiifUrl: String?,
    val website: String?
)
