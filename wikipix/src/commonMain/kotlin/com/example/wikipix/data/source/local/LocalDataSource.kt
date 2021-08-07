package com.example.wikipix.data.source.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent

class LocalDataSource(
    private val db: WikipixCache,
    private val backgroundDispatcher: CoroutineDispatcher
) : KoinComponent {
    fun loadUrl(title: String) =
        db.wikipixCacheQueries.loadUrl(title).asFlow().mapToOneOrNull()
            .flowOn(backgroundDispatcher)

    fun insertUrl(url: UrlEntity) = db.wikipixCacheQueries.insertUrl(url.title, url.url)
}
