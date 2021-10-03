package com.example.artic.domain.model

data class AgentModel(
    val id: Int,
    val title: String,
    val birthDate: Int?,
    val birthPlace: Int?,
    val deathDate: Int?,
    val deathPlace: Int?,
    val description: String?,
    val artworkIds: List<Int>?
) {
    companion object {
        val fakes = listOf(
            AgentModel(
                id = 1194,
                title = "John Atherton",
                birthDate = 1900,
                birthPlace = null,
                deathDate = 1952,
                deathPlace = null,
                description = null,
                artworkIds = null
            ),
            AgentModel(
                id = 37219,
                title = "Andy Warhol",
                birthDate = 1928,
                birthPlace = null,
                deathDate = 1987,
                deathPlace = null,
                description = "No artist is more closely associated with advertising, consumer culture, and mass media than Andy Warhol. Through his brazen appropriation of images and his radical use of the photo-emulsion silkscreen process, the artist became synonymous with Pop",
                artworkIds = listOf(226725, 227646)
            )
        )
    }
}
