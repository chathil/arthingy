package com.example.artic.domain.usecase

import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.repository.ArticRepository
import javax.inject.Inject

class ArticInteractor @Inject constructor(private val repository: ArticRepository) : ArticUseCase {
    override fun artworks(articConfig: ArticConfig) = repository.artworks(articConfig)
    override fun individualAgents(articConfig: ArticConfig) = repository.individualAgents(articConfig)
}
