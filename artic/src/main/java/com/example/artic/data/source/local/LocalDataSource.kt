package com.example.artic.data.source.local

import com.example.artic.data.source.local.entity.ArtworkEntity
import com.example.artic.data.source.local.room.ArticDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val articDao: ArticDao) {
    suspend fun freshInsertArtworks(arts: List<ArtworkEntity>, page: Int) = articDao.freshInsertArtworks(arts, page)
    suspend fun insertArtworks(arts: List<ArtworkEntity>) = articDao.insertArtworks(arts)
    fun loadArtworks() = articDao.loadArtworks()
    suspend fun artworkLastUpdated() = articDao.artworkLastUpdated()
    fun delete(id: Int) = articDao.delete(id)
    fun deleteByPage(page: Int) = articDao.deleteByPage(page)
    fun deleteAll() = articDao.deleteAll()
}
