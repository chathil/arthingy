package com.example.artic.di

import com.example.artic.domain.usecase.ArticInteractor
import com.example.artic.domain.usecase.ArticUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun provideUseCase(articInteractor: ArticInteractor): ArticUseCase
}
