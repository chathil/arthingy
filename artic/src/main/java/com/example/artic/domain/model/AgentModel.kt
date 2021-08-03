package com.example.artic.domain.model

data class AgentModel(
    val id: Int,
    val title: String?,
    val birthDate: Int?,
    val birthPlace: Int?,
    val deathDate: Int?,
    val deathPlace: Int?,
    val description: String?,
    val artworkIds: List<Int>?
)
