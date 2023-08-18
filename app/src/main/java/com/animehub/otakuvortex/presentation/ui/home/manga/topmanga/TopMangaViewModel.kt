package com.animehub.otakuvortex.presentation.ui.home.manga.topmanga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.manga.TopMangaUseCase
import com.animehub.otakuvortex.presentation.ui.home.anime.topanime.TopAnimeListState
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopMangaViewModel @Inject constructor(
    private val topMangaUseCase: TopMangaUseCase
): ViewModel() {

    private val topMangaListValue = MutableStateFlow(TopMangaListState())
    var _topMangaListValue: StateFlow<TopMangaListState> = topMangaListValue

    fun getTopMangaList(page: Int) = viewModelScope.launch (Dispatchers.IO){
        topMangaUseCase(page=page).collect{
            when (it) {
                is ResponseState.Success -> {
                    topMangaListValue.value =
                        TopMangaListState(topMangaList = it.data ?: emptyList())
                }
                is ResponseState.Loading -> {
                    topMangaListValue.value = TopMangaListState(isLoading = true)
                }
                is ResponseState.Error -> {
                    topMangaListValue.value =
                        TopMangaListState(error = it.message ?: "An Unexcepted Error")
                }
            }
        }
    }

}