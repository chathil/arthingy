package com.example.wikipix.data.source.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(
    private val db: WikipixCache,
    private val backgroundDispatcher: CoroutineDispatcher,
) {
    fun loadUrl(title: String): Flow<String?> {
        return db.wikipixCacheQueries.loadUrl(title).asFlow().mapToOneOrNull()
            .flowOn(backgroundDispatcher)
    }

    fun insertUrl(url: UrlEntity) = db.wikipixCacheQueries.insertUrl(url.title, url.url)
    companion object {
        private val TAG = LocalDataSource::class.simpleName ?: "LocalDataSource"
    }
}
