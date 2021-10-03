package com.example.artic.domain.usecase

import com.dropbox.android.external.store4.Store
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.model.ArtworkTypeModel
import com.example.artic.domain.repository.ArticRepository
import javax.inject.Inject

class ArticInteractor @Inject constructor(private val repository: ArticRepository) : ArticUseCase {
    override fun pagedArtworks(articConfig: ArticConfig) = repository.pagedArtworks(articConfig)
    override fun artworks(articConfig: ArticConfig) = repository.artworks(articConfig)

    override fun artworkTypes(articConfig: ArticConfig): Store<Int, List<ArtworkTypeModel>> = repository.artworkTypes(articConfig)

    override fun pagedAgents(articConfig: ArticConfig) = repository.pagedAgents(articConfig)
    override fun agents(articConfig: ArticConfig) = repository.agents(articConfig)

    override fun audios(articConfig: ArticConfig) = repository.audios(articConfig)
}
