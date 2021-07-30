package com.example.uimain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.artic.domain.usecase.ArticUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: ArticUseCase
) : ViewModel() {

    // For reference
//    private val _pets = MutableStateFlow(emptyList<PetModel>())
//    val pets: StateFlow<List<PetModel>>
//        get() = _pets

    // Each view has it's own loading status
    private val _isLoading = MutableStateFlow(emptyList<HomeViewComponent>())
    val isLoading: StateFlow<List<HomeViewComponent>>
        get() = _isLoading

    private val _isError = MutableStateFlow<String?>(null)
    val isError: StateFlow<String?>
        get() = _isError

    fun artworks() = useCase.artworks()

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}
