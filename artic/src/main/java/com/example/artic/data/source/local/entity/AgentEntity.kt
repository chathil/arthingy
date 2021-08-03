package com.example.artic.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artic.domain.model.AgentModel

@Entity(tableName = "agent")
data class AgentEntity(
    @PrimaryKey
    val id: Int,
    val lastUpdated: Long = System.currentTimeMillis(),
    val agentLastUpdated: Long,
    val totalPages: Int,
    val currentPage: Int,
    val title: String?,
    val birthDate: Int?,
    val birthPlace: Int?,
    val deathDate: Int?,
    val deathPlace: Int?,
    val description: String?,
    val artworkIds: List<Int>?
)

fun AgentEntity.asDomainModel() =
    AgentModel(id, title, birthDate, birthPlace, deathDate, deathPlace, description, artworkIds)

fun List<AgentEntity>.asDomainModels() = map { it.asDomainModel() }
