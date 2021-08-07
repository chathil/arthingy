package com.example.uimain

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artic.domain.usecase.ArticUseCase
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * To avoid using Hilt with actual/ expect pattern in KMM.
 * And this module in only used here.
 */
@InstallIn(SingletonComponent::class)
@Module
class WikipixModule {
    @Provides
    @Singleton
    fun provideWikipixUseCase(@ApplicationContext context: Context): WikipixUseCase {
        return WikipixSdk(DatabaseDriverFactory(context)).provideUseCase()
    }
}

@OptIn(InternalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articUseCase: ArticUseCase,
    private val wikipixUseCase: WikipixUseCase
) : ViewModel() {

    // For reference
//    private val _pets = MutableStateFlow(emptyList<PetModel>())
//    val pets: StateFlow<List<PetModel>>
//        get() = _pets

    init {
        viewModelScope.launch {
            wikipixUseCase.loadUrl("United States").collect {
                when (it) {
                    is Resource.Error -> Log.e(TAG, "${it.message}")
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> Log.i(TAG, "${it.data}")
                }
            }
        }
    }

    // Each view has it's own loading status
    private val _isLoading = MutableStateFlow(emptyList<HomeViewComponent>())
    val isLoading: StateFlow<List<HomeViewComponent>>
        get() = _isLoading

    private val _isError = MutableStateFlow<String?>(null)
    val isError: StateFlow<String?>
        get() = _isError

    fun artworks() = articUseCase.artworks()
    fun individualAgents() = articUseCase.individualAgents()

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}
