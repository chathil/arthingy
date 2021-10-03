package com.example.artic.domain.model

class ArticImage(
    private val identifier: String?,
    private val size: String = "full",
    private val quality: Quality = Quality.W400,
    private val rotation: Int = 0,
) {
    enum class Quality(val str: String) {
        W200("200,"),
        W400("400,"),
        W600("600,"),
        W843("843,")
    }

    private val format = "default.jpg"

    fun requestUrl(): String {
        val res = "$baseUrl/$identifier/$size/${quality.str}/$rotation/$format"
        res
        return res
    }

    companion object {
        private const val baseUrl = "https://www.artic.edu/iiif/2/"
    }
}
