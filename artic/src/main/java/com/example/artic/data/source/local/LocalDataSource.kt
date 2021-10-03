package com.example.artic.data.source.local

import com.example.artic.data.source.local.entity.AgentEntity
import com.example.artic.data.source.local.entity.ArtworkEntity
import com.example.artic.data.source.local.entity.ArtworkTypeEntity
import com.example.artic.data.source.local.entity.AudioEntity
import com.example.artic.data.source.local.room.ArticDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val articDao: ArticDao) {
    // Artworks
    suspend fun freshInsertArtworks(arts: List<ArtworkEntity>, page: Int) =
        articDao.freshInsertArtworks(arts, page)

    suspend fun insertArtworks(arts: List<ArtworkEntity>) = articDao.insertArtworks(arts)

    fun loadPagedArtworks() = articDao.loadPagedArtworks()

    fun loadArtworks(limit: Int) = articDao.loadArtworks(limit)

    fun deleteAllArtworks() = articDao.deleteAllArtworks()

    fun deleteArtwork(id: Int) = articDao.deleteArtwork(id)

    // Artwork Types
    suspend fun freshInsertArtworkTypes(types: List<ArtworkTypeEntity>, page: Int) =
        articDao.freshInsertArtworkTypes(types, page)

    suspend fun insertArtworkTypes(types: List<ArtworkTypeEntity>) =
        articDao.insertArtworkTypes(types)

    fun loadPagedArtworkTypes() = articDao.loadPagedArtworkTypes()

    fun loadArtworkTypes(limit: Int) = articDao.loadArtworkTypes(limit)

    fun deleteAllArtworkTypes() = articDao.deleteAllArtworkTypes()

    fun deleteArtworkType(id: Int) = articDao.deleteArtwork(id)

    // Agents
    suspend fun freshInsertAgents(agents: List<AgentEntity>, page: Int) =
        articDao.freshInsertAgents(agents, page)

    suspend fun insertAgents(agents: List<AgentEntity>) = articDao.insertAgents(agents)

    fun loadPagedAgents() = articDao.loadPagedAgents()

    fun loadAgents(limit: Int) = articDao.loadAgents(limit)

    fun deleteAllAgents() = articDao.deleteAllAgents()

    fun deleteAgent(id: Int) = articDao.deleteAgent(id)

    // Audio
    suspend fun freshInsertAudios(audios: List<AudioEntity>, page: Int) =
        articDao.freshInsertAudios(audios, page)

    suspend fun insertAudios(audios: List<AudioEntity>) = articDao.insertAudios(audios)

    fun loadPagedAudios() = articDao.loadPagedAudios()

    fun loadAudios(limit: Int) = articDao.loadAudios(limit)

    fun deleteAllAudios() = articDao.deleteAllAudios()

    fun deleteAudio(id: Int) = articDao.deleteAudio(id)
}
