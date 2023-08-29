package com.animehub.otakuvortex.presentation.ui.info.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.anime.AnimeByIdUseCase
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeByIdViewModel@Inject constructor(
    private val useCase: AnimeByIdUseCase
): ViewModel() {

    private val _animeByIdListValue = MutableStateFlow(AnimeByIdListState())
    var animeByIdListValue: StateFlow<AnimeByIdListState> = _animeByIdListValue

    fun getAnimeById(amineId: Int){
        viewModelScope.launch(Dispatchers.IO){
            useCase(amineId).collect{ responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val animeByIdListState = AnimeByIdListState(animeById = responseState.data)
                        _animeByIdListValue.value = animeByIdListState
                    }
                    is ResponseState.Loading -> {
                        val animeByIdListState = AnimeByIdListState(isLoading = true)
                        _animeByIdListValue.value = animeByIdListState
//                        Log.i("load", _topAnimeListValue.value.toString())
                    }
                    is ResponseState.Error -> {
                        val animeByIdListState = AnimeByIdListState(error = responseState.message ?: "An Unexpected Error")
                        _animeByIdListValue.value = animeByIdListState
//                        Log.i("error", _topAnimeListValue.value.toString())
                    }
                }
            }
        }
    }

}