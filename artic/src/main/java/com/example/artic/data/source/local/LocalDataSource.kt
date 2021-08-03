package com.example.artic.data.source.local

import com.example.artic.data.source.local.entity.AgentEntity
import com.example.artic.data.source.local.entity.ArtworkEntity
import com.example.artic.data.source.local.room.ArticDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val articDao: ArticDao) {
    suspend fun freshInsertArtworks(arts: List<ArtworkEntity>, page: Int) =
        articDao.freshInsertArtworks(arts, page)

    suspend fun insertArtworks(arts: List<ArtworkEntity>) = articDao.insertArtworks(arts)
    fun loadArtworks() = articDao.loadArtworks()

    suspend fun freshInsertAgents(agents: List<AgentEntity>, page: Int) =
        articDao.freshInsertAgents(agents, page)

    suspend fun insertAgents(agents: List<AgentEntity>) = articDao.insertAgents(agents)
    fun loadAgents() = articDao.loadAgents()
}
