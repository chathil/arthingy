package com.example.wikipix.util

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.takeFrom
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.native.concurrent.ThreadLocal

@OptIn(InternalSerializationApi::class)
suspend inline fun <reified T : Any> HttpClient.getData(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): T {
    val response = get<HttpResponse> {
        url.takeFrom(urlString)
        block()
    }
    val jsonBody = response.readText()
    return json.decodeFromString(T::class.serializer(), jsonBody)
}

@ThreadLocal
val json by lazy {
    Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
}
