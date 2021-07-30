package com.example.artic.di

import android.content.Context
import androidx.room.Room
import com.example.artic.data.source.local.room.ArticDao
import com.example.artic.data.source.local.room.ArticDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providesComposeKitDao(database: ArticDatabase): ArticDao {
        return database.dao
    }
    @Provides
    @Singleton
    fun provideComposeKitDatabase(@ApplicationContext context: Context): ArticDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ArticDatabase::class.java,
            "artic_cache"
        ).build()
    }
}
