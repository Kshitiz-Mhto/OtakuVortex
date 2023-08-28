package com.animehub.otakuvortex.presentation.ui.search.searchanime

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.anime.SearchedAnimeUseCase
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchedAnimeViewModel @Inject constructor(
    private val useCase: SearchedAnimeUseCase
): ViewModel() {

    init {
        getSearchedAnimeList()
    }

    private val _searchedAnimeListValue = MutableStateFlow(SearchedAnimeListState())
    var searchedAnimeListValue: StateFlow<SearchedAnimeListState> = _searchedAnimeListValue

    fun getSearchedAnimeList() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase("one piece").collect{ responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val searchedAnimePagingData = SearchedAnimeListState(searchedAnimeList = responseState.data)
                        _searchedAnimeListValue.value = searchedAnimePagingData
                    }
                    is ResponseState.Loading -> {
                        val searchedAnimeListState = SearchedAnimeListState(isLoading = true)
                        _searchedAnimeListValue.value = searchedAnimeListState
                    }
                    is ResponseState.Error -> {
                        val searchedAnimeListState = SearchedAnimeListState(error = responseState.message ?: "An Unexpected Error")
                        _searchedAnimeListValue.value = searchedAnimeListState
                    }
                }
            }
        }
    }

}