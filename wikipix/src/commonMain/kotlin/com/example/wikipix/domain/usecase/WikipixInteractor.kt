package com.example.wikipix.domain.usecase

import co.touchlab.stately.ensureNeverFrozen
import com.example.wikipix.data.Resource
import com.example.wikipix.domain.repository.WikipixRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class WikipixInteractor(private val repository: WikipixRepository) : WikipixUseCase {
    private val scope = MainScope()

    override fun loadUrl(title: String) = repository.loadUrl(title)
    override suspend fun loadUrlAsync(title: String) = repository.loadUrlAsync(title)

    var urlJob: Job? = null

    init {
        ensureNeverFrozen()
    }

    /**
     * Called from iOS
     */
    @InternalCoroutinesApi
    @OptIn(FlowPreview::class)
    override fun observeUrl(title: String, completionHandler: (result: Resource<String?>) -> Unit) {
        urlJob = scope.launch {
            loadUrl(title).collect {
                completionHandler(it)
            }
        }
    }

    /**
     * Called from iOS
     */
    override fun onDestroy() {
        urlJob?.cancel()
    }
}
