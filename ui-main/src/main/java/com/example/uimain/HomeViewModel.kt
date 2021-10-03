package com.example.uimain

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.map
import co.touchlab.kermit.Kermit
import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.example.artic.data.source.remote.network.ArticConfig
import com.example.artic.domain.model.ArtworkModel
import com.example.artic.domain.usecase.ArticUseCase
import com.example.commonui.component.ViewComponent
import com.example.wikipix.data.Resource
import com.example.wikipix.di.DatabaseDriverFactory
import com.example.wikipix.di.WikipixSdk
import com.example.wikipix.domain.usecase.WikipixUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

/**
 * To avoid using Hilt with actual/ expect pattern in KMM.
 * And this module is only used here.
 */
@InstallIn(SingletonComponent::class)
@Module
class WikipixModule {
    @Provides
    @Singleton
    fun provideWikipixUseCase(@ApplicationContext context: Context): WikipixUseCase {
        return WikipixSdk(DatabaseDriverFactory(context), Kermit()).provideUseCase()
    }
}

@OptIn(InternalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articUseCase: ArticUseCase,
    private val wikipixUseCase: WikipixUseCase
) : ViewModel() {

    private val _arts = MutableStateFlow(emptyMap<ArtworkModel, String>())
    val arts = _arts.asStateFlow()

    // Each view has it's own loading status
    private val _isLoading = MutableStateFlow(emptyList<ViewComponent>())
    val isLoading: StateFlow<List<ViewComponent>>
        get() = _isLoading

    private val _isError = MutableStateFlow<String?>(null)
    val isError: StateFlow<String?>
        get() = _isError

    fun artworks() = articUseCase.artworks().stream(StoreRequest.cached(-1, true))
        .let { storeReduce(it, HomeViewComponent.ARTWORK) }.filterNotNull()

    fun artworkTypes() = articUseCase.artworkTypes().stream(StoreRequest.cached(-1, true))
        .let { storeReduce(it, HomeViewComponent.ARTWORK_TYPE) }.filterNotNull()

    fun pagedArtworks() = articUseCase.pagedArtworks()
        .onStart { _isLoading.value += HomeViewComponent.ENDLESS_ARTWORK }
        .onCompletion { _isLoading.value -= HomeViewComponent.ENDLESS_ARTWORK }

    fun individualAgents() = articUseCase.agents().stream(StoreRequest.cached(-1, true))
        .let { storeReduce(it, HomeViewComponent.AGENT) }.map { agents ->
            agents?.map { agent ->
                agent to wikipixUseCase.loadUrlAsync(agent.title)
            }
        }.filterNotNull()

    fun audios() = articUseCase.audios(articConfig = ArticConfig(30)).stream(StoreRequest.cached(-1, true))
        .let { storeReduce(it, HomeViewComponent.STORIES) }.filterNotNull().map {
            it
        }

    private inline fun <reified T> resourceReduce(
        input: Flow<Resource<T>>,
        view: ViewComponent
    ): Flow<T?> {
        return input.map { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _isLoading.value += view
                    Log.i(TAG, "reducing ${T::class.java.simpleName}")
                    resource.data
                }
                is Resource.Success -> {
                    _isLoading.value -= view
                    Log.i(TAG, "Success: ${T::class.java.simpleName}: ${resource.data}")
                    resource.data
                }
                is Resource.Error -> {
                    _isLoading.value -= view
                    _isError.value = "Error fetching artworks"
                    Log.e(
                        TAG,
                        "Failed to reduce ${T::class.java.simpleName}: ${resource.message}"
                    )
                    resource.data
                }
            }
        }
    }

    private inline fun <reified T> storeReduce(
        input: Flow<StoreResponse<T>>,
        view: ViewComponent
    ): Flow<T?> {
        return input.map { response ->
            when (response) {
                is StoreResponse.Data<*> -> {
                    if (response.origin == ResponseOrigin.Fetcher)
                        _isLoading.value -= view
                    Log.i(TAG, "Success: ${T::class.java.simpleName}: ${response.requireData()}")
                    response.dataOrNull()
                }
                is StoreResponse.Loading -> {
                    _isLoading.value += view
                    Log.i(TAG, "reducing ${T::class.java.simpleName}")
                    response.dataOrNull()
                }
                is StoreResponse.Error -> {
                    if (response.origin == ResponseOrigin.Fetcher)
                        _isLoading.value -= view
                    _isError.value = "Error fetching artworks"
                    Log.e(
                        TAG,
                        "Failed to reduce ${T::class.java.simpleName}: ${response.errorMessageOrNull()}"
                    )
                    response.dataOrNull()
                }
                is StoreResponse.NoNewData -> {
                    if (response.origin == ResponseOrigin.Fetcher)
                        _isLoading.value -= view
                    Log.i(TAG, "No new data of type ${T::class.java.simpleName}")
                    response.dataOrNull()
                }
            }
        }
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}
