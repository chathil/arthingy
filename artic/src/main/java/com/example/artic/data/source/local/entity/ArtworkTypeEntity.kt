package com.example.artic.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artic.domain.model.ArtworkTypeModel

@Entity(tableName = "artworkType")
data class ArtworkTypeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val totalPage: Int,
    val currentPage: Int,
    val lastUpdated: Long = System.currentTimeMillis(),
    val typeLastUpdate: Long
)

fun ArtworkTypeEntity.asDomainModel() = ArtworkTypeModel(id, title)

fun List<ArtworkTypeEntity>.asDomainModels() = map { it.asDomainModel() }
