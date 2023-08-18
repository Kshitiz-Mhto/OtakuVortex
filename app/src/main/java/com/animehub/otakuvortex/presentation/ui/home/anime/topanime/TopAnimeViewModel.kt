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

    private val topAnimeListValue = MutableStateFlow(TopAnimeListState())
    var _topAnimeListValue: StateFlow<TopAnimeListState> = topAnimeListValue

    fun getTopAnimeList(page:Int) = viewModelScope.launch(Dispatchers.IO){
        topAnimeUseCase(page = page).collect{
            when(it){
                is ResponseState.Success -> {
                    topAnimeListValue.value = TopAnimeListState(topAnimeList = it.data?: emptyList())
                }
                is ResponseState.Loading -> {
                    topAnimeListValue.value = TopAnimeListState(isLoading = true)
                }
                is ResponseState.Error -> {
                    topAnimeListValue.value = TopAnimeListState(error = it.message ?: "An Unexcepted Error")
                }
            }
        }
    }
}