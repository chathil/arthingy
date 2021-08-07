package com.example.wikipix.domain.usecase

import com.example.wikipix.domain.repository.WikipixRepository
import org.koin.core.component.KoinComponent

internal class WikipixInteractor(private val repository: WikipixRepository) : WikipixUseCase, KoinComponent {
    override fun loadUrl(title: String) = repository.loadUrl(title)
}
