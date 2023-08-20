package com.animehub.otakuvortex.presentation.ui.home.anime.topanime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.anime.TopAnimeUseCase
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAnimeViewModel @Inject constructor(
    private val topAnimeUseCase: TopAnimeUseCase
): ViewModel() {

    init {
        getTopAnimeList()
    }

    private val _topAnimeListValue = MutableStateFlow(TopAnimeListState())
    var topAnimeListValue: StateFlow<TopAnimeListState> = _topAnimeListValue


    fun getTopAnimeList() {
        viewModelScope.launch(Dispatchers.IO) {
            topAnimeUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val topAnimePagingData = TopAnimeListState(topAnimeList = responseState.data)
                        _topAnimeListValue.value = topAnimePagingData
//                        Log.i("value", responseState.data.toString())
//                        Log.i("value1", _topAnimeListValue.value.toString())
                        }
                    is ResponseState.Loading -> {
                        val topAnimeListState = TopAnimeListState(isLoading = true)
                        _topAnimeListValue.value = (topAnimeListState)
//                        Log.i("load", _topAnimeListValue.value.toString())
                    }
                    is ResponseState.Error -> {
                        val topAnimeListState = TopAnimeListState(error = responseState.message ?: "An Unexpected Error")
                        _topAnimeListValue.value = topAnimeListState
//                        Log.i("error", _topAnimeListValue.value.toString())
                    }
                }
            }
        }
    }

}