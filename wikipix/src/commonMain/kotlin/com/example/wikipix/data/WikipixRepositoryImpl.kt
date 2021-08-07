package com.example.wikipix.data

import com.example.wikipix.data.source.local.LocalDataSource
import com.example.wikipix.data.source.local.UrlEntity
import com.example.wikipix.data.source.remote.RemoteDataSource
import com.example.wikipix.domain.repository.WikipixRepository
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class WikipixRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : WikipixRepository, KoinComponent {
    override fun loadUrl(title: String) = object : NetworkBoundResource<String?, String>() {
        override fun loadFromDB() = localDataSource.loadUrl(title).map { it?.url }

        override fun shouldFetch(data: String?) = data.isNullOrEmpty()

        override suspend fun createCall() = remoteDataSource.getUrl(title)

        override suspend fun saveCallResult(data: String) =
            localDataSource.insertUrl(UrlEntity(title, data))
    }.asFlow()
}
