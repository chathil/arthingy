package com.example.artic.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.artic.domain.model.ArtworkConfigModel
import com.example.artic.domain.model.ArtworkCopyrightModel
import com.example.artic.domain.model.ArtworkModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "artwork")
data class ArtworkEntity(
    @PrimaryKey
    val id: Int,
    val lastUpdated: Long = System.currentTimeMillis(),
    val artLastUpdated: Long,
    val totalPages: Int,
    val currentPage: Int,
    val apiModel: String?,
    val apiLink: String?,
    val isBoosted: Boolean?,
    val title: String?,
    val altTitles: List<String>,
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
    val altArtistIds: List<Int>,
    val artistIds: List<Int>,
    val artistTitles: List<String>,
    val imageId: String?,
    val altImageIds: List<String>,
    val documentIds: List<String>,
    val soundIds: List<String>,
    val textIds: List<String>,
    val info: ArtworkCopyrightEntity?,
    val config: ArtworkConfigEntity?
)

fun ArtworkEntity.asDomainModel() = ArtworkModel(
    id = id,
    lastUpdated = lastUpdated,
    totalPages = totalPages,
    currentPage = currentPage,
    apiModel = apiModel,
    apiLink = apiLink,
    isBoosted = isBoosted,
    title = title,
    altTitles = altTitles,
    mainReferenceNumber = mainReferenceNumber,
    hasNotBeenViewedMuch = hasNotBeenViewedMuch,
    boostRank = boostRank,
    dateStart = dateStart,
    dateEnd = dateEnd,
    dateDisplay = dateDisplay,
    dateQualifierTitle = dateQualifierTitle,
    dateQualifierId = dateQualifierId,
    artistDisplay = artistDisplay,
    placeOfOrigin = placeOfOrigin,
    dimensions = dimensions,
    mediumDisplay = mediumDisplay,
    inscriptions = inscriptions,
    creditLine = creditLine,
    publicationHistory = publicationHistory,
    exhibitionHistory = exhibitionHistory,
    provenanceText = provenanceText,
    publishingVerificationLevel = publishingVerificationLevel,
    internalDepartmentId = internalDepartmentId,
    fiscalYear = fiscalYear,
    isZoomable = isZoomable,
    colorfulness = colorfulness,
    latlon = latlon,
    isOnView = isOnView,
    onLoanDisplay = onLoanDisplay,
    galleryTitle = galleryTitle,
    galleryId = galleryId,
    artworkTypeTitle = artworkTypeTitle,
    artworkTypeId = artworkTypeId,
    departmentId = departmentId,
    departmentTitle = departmentTitle,
    artistId = artistId,
    artistTitle = artistTitle,
    altArtistIds = altArtistIds,
    artistIds = artistIds,
    artistTitles = artistTitles,
    imageId = imageId,
    altImageIds = altImageIds,
    documentIds = documentIds,
    soundIds = soundIds,
    textIds = textIds,
    info = info?.asDomainModel(),
    config = config?.asDomainModel()
)

@Serializable
data class ArtworkCopyrightEntity(
    val licenseText: String?,
    val licenseLinks: List<String>?,
    val version: String?
)

fun ArtworkCopyrightEntity.asDomainModel() = ArtworkCopyrightModel(licenseText, licenseLinks, version)

@Serializable
data class ArtworkConfigEntity(
    val iiifUrl: String?,
    val website: String?
)

fun ArtworkConfigEntity.asDomainModel() = ArtworkConfigModel(iiifUrl, website)

class StringTypeConverters {
    @TypeConverter
    fun stringsToJson(strings: List<String>) =
        Json.encodeToString(strings)

    @TypeConverter
    fun jsonToStrings(json: String) = Json.decodeFromString<List<String>>(json)
}

class IntTypeConverters {
    @TypeConverter
    fun intsToJson(ints: List<Int>) = Json.encodeToString(ints)

    @TypeConverter
    fun jsonToInts(json: String) = Json.decodeFromString<List<Int>>(json)
}

class ArtworkTypeConverters {
    @TypeConverter
    fun copyrightToJson(copyright: ArtworkCopyrightEntity) = Json.encodeToString(copyright)

    @TypeConverter
    fun jsonToCopyright(json: String) = Json.decodeFromString<ArtworkCopyrightEntity>(json)

    @TypeConverter
    fun configToJson(config: ArtworkConfigEntity) = Json.encodeToString(config)

    @TypeConverter
    fun jsonToConfig(json: String) = Json.decodeFromString<ArtworkConfigEntity>(json)
}
