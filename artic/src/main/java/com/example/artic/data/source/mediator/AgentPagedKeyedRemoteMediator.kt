package com.example.artic.data.source.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.artic.data.source.local.LocalDataSource
import com.example.artic.data.source.local.entity.AgentEntity
import com.example.artic.data.source.remote.RemoteDataSource
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.data.source.remote.response.asEntities
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class AgentPagedKeyedRemoteMediator(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val query: ArticConfig
) : RemoteMediator<Int, AgentEntity>() {

    // TODO: 03/08/21 Make this timer work
    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
        return if (false) {
            // Cached data is up-to-date, so there is no need to re-fetch from network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning LAUNCH_INITIAL_REFRESH here
            // will also block RemoteMediator's APPEND and PREPEND from running until REFRESH
            // succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AgentEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.currentPage + 1
                }
            }
            val response =
                remote.individualAgents(
                    "",
                    query.fields ?: "",
                    state.config.pageSize,
                    loadKey ?: 1
                )
                    .asEntities()
            if (loadType == LoadType.REFRESH)
                local.freshInsertAgents(response, response.firstOrNull()?.currentPage ?: 1)
            else
                local.insertAgents(response)
            MediatorResult.Success(endOfPaginationReached = response.firstOrNull()?.currentPage == response.firstOrNull()?.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    companion object {
        private val TAG = AgentPagedKeyedRemoteMediator::class.java.simpleName
    }
}
