package com.animehub.otakuvortex.presentation.ui.info.manga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.manga.MangaByIdUseCase
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaByIdViewModel@Inject constructor(
    private val useCase: MangaByIdUseCase
): ViewModel() {

    private val _mangaByIdListValue = MutableStateFlow(MangaByIdListState())
    var mangaByIdListValue: StateFlow<MangaByIdListState> = _mangaByIdListValue

    fun getMangaById(mangaId: Int){
        viewModelScope.launch(Dispatchers.IO){
            useCase(mangaId).collect{ responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val mangaByIdListState = MangaByIdListState(mangaById = responseState.data)
                        _mangaByIdListValue.value = mangaByIdListState
                    }
                    is ResponseState.Loading -> {
                        val mangaByIdListState = MangaByIdListState(isLoading = true)
                        _mangaByIdListValue.value = mangaByIdListState
//                        Log.i("load", _topAnimeListValue.value.toString())
                    }
                    is ResponseState.Error -> {
                        val mangaByIdListState = MangaByIdListState(error = responseState.message ?: "An Unexpected Error")
                        _mangaByIdListValue.value = mangaByIdListState
//                        Log.i("error", _topAnimeListValue.value.toString())
                    }
                }
            }
        }
    }
}