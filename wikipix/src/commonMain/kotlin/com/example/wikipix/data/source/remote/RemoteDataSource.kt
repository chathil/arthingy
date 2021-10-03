package com.example.wikipix.data.source.remote

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.example.wikipix.data.source.remote.network.ApiResponse
import com.example.wikipix.data.source.remote.response.UrlResponse
import com.example.wikipix.util.getData
import com.example.wikipix.util.handleErrors
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.jsonObject

class RemoteDataSource(
    private val baseUrl: String = "https://en.wikipedia.org",
    private val httpClient: HttpClient,
    private val log: Kermit
) {

    init {
        ensureNeverFrozen()
    }

    fun getUrl(title: String) = flow {
        val response = httpClient.getData<UrlResponse>("$baseUrl/w/api.php") {
            parameter("action", "query")
            parameter("prop", "pageimages")
            parameter("format", "json")
            parameter("piprop", "original")
            parameter("titles", title)
        }.query?.get("pages")?.jsonObject?.entries?.first()?.value?.jsonObject?.get("original")?.jsonObject?.get(
            "source"
        )?.toString()
        log.i(tag = TAG) { response ?: "Nothing to log" }
        if (response.isNullOrEmpty())
            emit(ApiResponse.Empty)
        else
            emit(ApiResponse.Success(response))
    }.handleErrors()/*.flowOn(Dispatchers.IO)*/ // TODO: 05/08/21 Dispatchers.IO doesn't exist

    companion object {
        @Suppress("UNUSED")
        private val TAG = RemoteDataSource::class.simpleName ?: "RemoteDataSource"
    }
}
