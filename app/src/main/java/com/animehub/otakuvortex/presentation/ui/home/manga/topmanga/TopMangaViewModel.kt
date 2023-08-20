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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopMangaViewModel @Inject constructor(
    private val topMangaUseCase: TopMangaUseCase
): ViewModel() {

    init {
        getTopMangaList()
    }

    private val _topMangaListValue = MutableStateFlow(TopMangaListState())
    var topMangaListValue: StateFlow<TopMangaListState> = _topMangaListValue

    fun getTopMangaList() = viewModelScope.launch (Dispatchers.IO){
        topMangaUseCase().collect{
            when (it) {
                is ResponseState.Success -> {
                    _topMangaListValue.value =
                        TopMangaListState(topMangaList = it.data)
                }
                is ResponseState.Loading -> {
                    _topMangaListValue.value = TopMangaListState(isLoading = true)
                }
                is ResponseState.Error -> {
                    _topMangaListValue.value =
                        TopMangaListState(error = it.message ?: "An Unexcepted Error")
                }
            }
        }
    }

}