package com.example.artic.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dropbox.android.external.store4.Fetcher
import com.dropbox.android.external.store4.SourceOfTruth
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import com.example.artic.data.source.local.LocalDataSource
import com.example.artic.data.source.local.entity.asDomainModel
import com.example.artic.data.source.local.entity.asDomainModels
import com.example.artic.data.source.mediator.AgentPagedKeyedRemoteMediator
import com.example.artic.data.source.mediator.ArtworkPagedKeyedRemoteMediator
import com.example.artic.data.source.remote.RemoteDataSource
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.data.source.remote.response.asEntities
import com.example.artic.domain.model.AgentModel
import com.example.artic.domain.model.ArtworkModel
import com.example.artic.domain.model.ArtworkTypeModel
import com.example.artic.domain.model.AudioModel
import com.example.artic.domain.repository.ArticRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
class ArticRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : ArticRepository {

    override fun pagedArtworks(articConfig: ArticConfig) = Pager(
        config = PagingConfig(articConfig.limit),
        remoteMediator = ArtworkPagedKeyedRemoteMediator(local, remote)
    ) {
        local.loadPagedArtworks()
    }.flow.map { pagingData ->
        pagingData.map { it.asDomainModel() }
    }

    override fun artworks(articConfig: ArticConfig): Store<Int, List<ArtworkModel>> {
        return StoreBuilder.from(
            fetcher = Fetcher.of { remote.artworks(1, articConfig.limit).asEntities() },
            sourceOfTruth = SourceOfTruth.of(
                reader = { local.loadArtworks(articConfig.limit).map { it.asDomainModels() } },
                writer = { _, arts -> local.insertArtworks(arts) },
                delete = { id: Int -> local.deleteArtwork(id) },
                deleteAll = local::deleteAllArtworks
            )
        ).build()
    }

    override fun pagedArtworkTypes(articConfig: ArticConfig): Flow<PagingData<ArtworkTypeModel>> {
        TODO("Not yet implemented")
    }

    override fun artworkTypes(articConfig: ArticConfig): Store<Int, List<ArtworkTypeModel>> {
        return StoreBuilder.from(
            fetcher = Fetcher.of { remote.artworkTypes(1, articConfig.limit).asEntities() },
            sourceOfTruth = SourceOfTruth.of(
                reader = { local.loadArtworkTypes(articConfig.limit).map { it.asDomainModels() } },
                writer = { _, types -> local.insertArtworkTypes(types) },
                delete = { id: Int -> local.deleteArtworkType(id) },
                deleteAll = local::deleteAllArtworks
            )
        ).build()
    }

    override fun pagedAgents(articConfig: ArticConfig) = Pager(
        config = PagingConfig(articConfig.limit),
        remoteMediator = AgentPagedKeyedRemoteMediator(local, remote, articConfig)
    ) {
        local.loadPagedAgents()
    }.flow.map { pagingData ->
        pagingData.map {
            it.asDomainModel()
        }
    }

    override fun agents(articConfig: ArticConfig): Store<Int, List<AgentModel>> {
        return StoreBuilder.from(
            fetcher = Fetcher.of {
                remote.individualAgents(
                    articConfig.params ?: "",
                    articConfig.fields ?: "",
                    articConfig.limit,
                    1
                ).asEntities()
            },
            sourceOfTruth = SourceOfTruth.of(
                reader = { local.loadAgents(articConfig.limit).map { it.asDomainModels() } },
                writer = { _, agents -> local.insertAgents(agents) },
                delete = { id: Int -> local.deleteAgent(id) },
                deleteAll = local::deleteAllAgents
            )
        ).build()
    }

    override fun pagedAudios(articConfig: ArticConfig): Flow<PagingData<AudioModel>> {
        TODO("Not yet implemented")
    }

    override fun audios(articConfig: ArticConfig): Store<Int, List<AudioModel>> {
        return StoreBuilder.from(
            fetcher = Fetcher.of { remote.audios(1, articConfig.limit).asEntities() },
            sourceOfTruth = SourceOfTruth.of(
                reader = { local.loadAudios(articConfig.limit).map { it.asDomainModels() } },
                writer = { _, audios -> local.insertAudios(audios) },
                delete = { id: Int -> local.deleteAudio(id) },
                deleteAll = local::deleteAllAudios
            )
        ).build()
    }

    companion object {
        @Suppress("UNUSED")
        private val TAG = ArticRepositoryImpl::class.java.simpleName
    }
}
