package com.example.artic.di

import com.example.artic.data.ArticRepositoryImpl
import com.example.artic.domain.repository.ArticRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(articRepositoryImpl: ArticRepositoryImpl): ArticRepository
}
