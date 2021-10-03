package com.example.artic.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.artic.data.source.local.entity.AgentEntity
import com.example.artic.data.source.local.entity.ArtworkEntity
import com.example.artic.data.source.local.entity.ArtworkTypeEntity
import com.example.artic.data.source.local.entity.AudioEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ArticDao {
    // Artworks
    @Transaction
    open suspend fun freshInsertArtworks(arts: List<ArtworkEntity>, page: Int) {
        deleteArtworksByPage(page)
        insertArtworks(arts)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArtworks(arts: List<ArtworkEntity>)

    @Query("SELECT * FROM artwork ORDER BY artLastUpdated DESC")
    abstract fun loadPagedArtworks(): PagingSource<Int, ArtworkEntity>

    @Query("SELECT * FROM artwork ORDER BY artLastUpdated DESC LIMIT :limit")
    abstract fun loadArtworks(limit: Int): Flow<List<ArtworkEntity>>

    @Query("SELECT lastUpdated FROM artwork ORDER BY lastUpdated DESC LIMIT 1")
    abstract suspend fun artworkLastUpdated(): Long?

    @Query("DELETE FROM artwork WHERE id = :id")
    abstract fun deleteArtwork(id: Int)

    @Query("DELETE FROM artwork WHERE currentPage = :page")
    abstract fun deleteArtworksByPage(page: Int)

    @Query("DELETE FROM artwork")
    abstract fun deleteAllArtworks()

    // Artwork Types
    @Transaction
    open suspend fun freshInsertArtworkTypes(types: List<ArtworkTypeEntity>, page: Int) {
        deleteArtworkTypesByPage(page)
        insertArtworkTypes(types)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertArtworkTypes(types: List<ArtworkTypeEntity>)

    @Query("SELECT * FROM artworkType ORDER BY typeLastUpdate DESC")
    abstract fun loadPagedArtworkTypes(): PagingSource<Int, ArtworkTypeEntity>

    @Query("SELECT * FROM artworkType ORDER BY typeLastUpdate DESC LIMIT :limit")
    abstract fun loadArtworkTypes(limit: Int): Flow<List<ArtworkTypeEntity>>

    @Query("SELECT lastUpdated FROM artworkType ORDER BY lastUpdated DESC LIMIT 1")
    abstract suspend fun artworkTypeLastUpdated(): Long?

    @Query("DELETE FROM artworkType WHERE id = :id")
    abstract fun deleteArtworkType(id: Int)

    @Query("DELETE FROM artworkType WHERE currentPage = :page")
    abstract fun deleteArtworkTypesByPage(page: Int)

    @Query("DELETE FROM artworkType")
    abstract fun deleteAllArtworkTypes()

    // Agents
    @Transaction
    open suspend fun freshInsertAgents(agents: List<AgentEntity>, page: Int) {
        deleteAgentsByPage(page)
        insertAgents(agents)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAgents(agents: List<AgentEntity>)

    @Query("SELECT * FROM agent ORDER BY agentLastUpdated DESC")
    abstract fun loadPagedAgents(): PagingSource<Int, AgentEntity>

    @Query("SELECT * FROM agent ORDER BY agentLastUpdated DESC LIMIT :limit")
    abstract fun loadAgents(limit: Int): Flow<List<AgentEntity>>

    @Query("SELECT lastUpdated FROM agent ORDER BY lastUpdated DESC LIMIT 1")
    abstract suspend fun agentLastUpdated(): Long?

    @Query("DELETE FROM agent WHERE id = :id")
    abstract fun deleteAgent(id: Int)

    @Query("DELETE FROM agent WHERE currentPage = :page")
    abstract fun deleteAgentsByPage(page: Int)

    @Query("DELETE from agent")
    abstract fun deleteAllAgents()

    // Audio
    open suspend fun freshInsertAudios(audios: List<AudioEntity>, page: Int) {
        deleteAudiosByPage(page)
        insertAudios(audios)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAudios(audios: List<AudioEntity>)

    @Query("SELECT * FROM audio ORDER BY audioLastUpdated DESC")
    abstract fun loadPagedAudios(): PagingSource<Int, AudioEntity>

    @Query("SELECT * FROM audio ORDER BY audioLastUpdated DESC LIMIT :limit")
    abstract fun loadAudios(limit: Int): Flow<List<AudioEntity>>

    @Query("SELECT lastUpdated FROM agent ORDER BY lastUpdated DESC LIMIT 1")
    abstract suspend fun audioLastUpdated(): Long?

    @Query("DELETE FROM audio WHERE id = :id")
    abstract fun deleteAudio(id: Int)

    @Query("DELETE FROM audio WHERE currentPage = :page")
    abstract fun deleteAudiosByPage(page: Int)

    @Query("DELETE FROM audio")
    abstract fun deleteAllAudios()
}
