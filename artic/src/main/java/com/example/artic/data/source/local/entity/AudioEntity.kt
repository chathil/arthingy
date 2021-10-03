package com.example.artic.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artic.domain.model.AudioModel

@Entity(tableName = "audio")
data class AudioEntity(
    @PrimaryKey
    val id: Int,
    val content: String,
    val artworkTitle: String,
    val totalPage: Int,
    val currentPage: Int,
    val lastUpdated: Long = System.currentTimeMillis(),
    val audioLastUpdated: Long
)

fun AudioEntity.asDomainModel() = AudioModel(id, content, artworkTitle)
fun List<AudioEntity>.asDomainModels() = map { it.asDomainModel() }
