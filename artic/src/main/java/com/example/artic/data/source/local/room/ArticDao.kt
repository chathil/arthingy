package com.example.artic.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.artic.data.source.local.entity.ArtworkEntity

@Dao
abstract class ArticDao {
    @Transaction
    open suspend fun freshInsertArtworks(arts: List<ArtworkEntity>, page: Int) {
        deleteByPage(page)
        insertArtworks(arts)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArtworks(arts: List<ArtworkEntity>)

    @Query("SELECT * FROM artwork ORDER BY artLastUpdated DESC")
    abstract fun loadArtworks(): PagingSource<Int, ArtworkEntity>

    @Query("SELECT lastUpdated FROM artwork ORDER BY lastUpdated DESC LIMIT 1")
    abstract suspend fun artworkLastUpdated(): Long?

    @Query("DELETE FROM artwork WHERE id = :id")
    abstract fun delete(id: Int)

    @Query("DELETE FROM artwork WHERE currentPage = :page")
    abstract fun deleteByPage(page: Int)

    @Query("DELETE FROM artwork")
    abstract fun deleteAll()
}
