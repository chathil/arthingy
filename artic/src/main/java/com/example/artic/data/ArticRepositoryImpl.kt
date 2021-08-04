package com.example.artic.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.artic.data.source.local.LocalDataSource
import com.example.artic.data.source.local.entity.asDomainModel
import com.example.artic.data.source.mediator.AgentPagedKeyedRemoteMediator
import com.example.artic.data.source.mediator.ArtworkPagedKeyedRemoteMediator
import com.example.artic.data.source.remote.RemoteDataSource
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.model.AgentModel
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

    override fun artworks(articConfig: ArticConfig) = Pager(
        config = PagingConfig(articConfig.limit),
        remoteMediator = ArtworkPagedKeyedRemoteMediator(local, remote)
    ) {
        local.loadArtworks()
    }.flow.map { pagingData ->
        pagingData.map { it.asDomainModel() }
    }

    override fun individualAgents(articConfig: ArticConfig): Flow<PagingData<AgentModel>> {
        Log.i(TAG, "individualAgents: Called")
        return Pager(
            config = PagingConfig(articConfig.limit),
            remoteMediator = AgentPagedKeyedRemoteMediator(local, remote, articConfig)
        ) {
            local.loadAgents()
        }.flow.map { pagingData ->
            pagingData.map {
                Log.i(TAG, "individualAgents: $it")
                it.asDomainModel()
            }
        }
    }

    companion object {
        @Suppress("UNUSED")
        private val TAG = ArticRepositoryImpl::class.java.simpleName
    }
}
