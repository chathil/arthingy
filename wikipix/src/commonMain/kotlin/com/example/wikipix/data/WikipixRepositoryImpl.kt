package com.example.wikipix.data

import co.touchlab.kermit.Kermit
import com.example.wikipix.data.source.local.LocalDataSource
import com.example.wikipix.data.source.local.UrlEntity
import com.example.wikipix.data.source.remote.RemoteDataSource
import com.example.wikipix.data.source.remote.network.ApiResponse
import com.example.wikipix.domain.repository.WikipixRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

/**
 * I use log because it works on both IDEs
 */
class WikipixRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val log: Kermit
) : WikipixRepository {
    override fun loadUrl(title: String) = object : NetworkBoundResource<String?, String>() {
        override fun loadFromDB(): Flow<String?> {
            return localDataSource.loadUrl(title).map {
                log.i(tag = TAG) { "loadFromDB $it" }
                it
            }
        }

        override fun shouldFetch(data: String?): Boolean {
            log.i(tag = TAG) { "shouldFetch $data" }
            return data.isNullOrEmpty()
        }

        override suspend fun createCall() = remoteDataSource.getUrl(title)

        override suspend fun saveCallResult(data: String) {
            log.i(tag = TAG) { "saveCallResult $data" }
            localDataSource.insertUrl(UrlEntity(title, data))
        }
    }.asFlow()

    override suspend fun loadUrlAsync(title: String): String? {
        val cache = localDataSource.loadUrl(title).first()
        if (cache == null) {
            val apiResponse = remoteDataSource.getUrl(title).firstOrNull()
            if (apiResponse is ApiResponse.Success) {
                localDataSource.insertUrl(UrlEntity(title, apiResponse.data))
                log.i(TAG) { "actual out: " + asThumbnail(apiResponse.data) }
                return asThumbnail(apiResponse.data)
            }
        }
        log.i(TAG) { "actual out: " + asThumbnail(cache) }
        return asThumbnail(cache)
    }

    private fun asThumbnail(url: String?, width: Int = 100): String? {
        val wikimediaBaseUrl = "https://upload.wikimedia.org/wikipedia/commons/"
        return if (url == null) {
            null
        } else wikimediaBaseUrl +
            "thumb/" +
            url.substringAfter(wikimediaBaseUrl).dropLast(1) +
            "/100px-" +
            url.substringAfterLast('/').dropLast(1)
    }

    companion object {
        private val TAG = WikipixRepositoryImpl::class.simpleName ?: "WikipixRepositoryImpl"
    }
}
