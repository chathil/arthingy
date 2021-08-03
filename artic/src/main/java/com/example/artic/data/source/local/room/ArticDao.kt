package com.example.artic.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.artic.data.source.local.entity.AgentEntity
import com.example.artic.data.source.local.entity.ArtworkEntity

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
    abstract fun loadArtworks(): PagingSource<Int, ArtworkEntity>

    @Query("SELECT lastUpdated FROM artwork ORDER BY lastUpdated DESC LIMIT 1")
    abstract suspend fun artworkLastUpdated(): Long?

    @Query("DELETE FROM artwork WHERE id = :id")
    abstract fun deleteArtwork(id: Int)

    @Query("DELETE FROM artwork WHERE currentPage = :page")
    abstract fun deleteArtworksByPage(page: Int)

    @Query("DELETE FROM artwork")
    abstract fun deleteAllArtworks()

    // Agents

    @Transaction
    open suspend fun freshInsertAgents(agents: List<AgentEntity>, page: Int) {
        deleteAgentsByPage(page)
        insertAgents(agents)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAgents(agents: List<AgentEntity>)

    @Query("SELECT * FROM agent ORDER BY agentLastUpdated DESC")
    abstract fun loadAgents(): PagingSource<Int, AgentEntity>

    @Query("SELECT lastUpdated FROM agent ORDER BY lastUpdated DESC LIMIT 1")
    abstract suspend fun agentLastUpdated(): Long?

    @Query("DELETE FROM agent WHERE id = :id")
    abstract fun deleteAgent(id: Int)

    @Query("DELETE FROM agent WHERE currentPage = :page")
    abstract fun deleteAgentsByPage(page: Int)

    @Query("DELETE from agent")
    abstract fun deleteAllAgents()
}
