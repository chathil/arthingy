package com.example.artic.domain.usecase

import com.example.artic.domain.repository.ArticRepository
import javax.inject.Inject

class ArticInteractor @Inject constructor(private val repository: ArticRepository) : ArticUseCase {
    override fun artworks() = repository.artworks()
    override fun individualAgents() = repository.individualAgents()
}
