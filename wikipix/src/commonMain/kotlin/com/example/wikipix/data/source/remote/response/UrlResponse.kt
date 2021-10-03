package com.example.wikipix.data.source.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class UrlResponse(
    val query: JsonObject?
)
