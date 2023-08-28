package com.animehub.otakuvortex.presentation.ui.search.searchmanga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.manga.SearchedMangaUseCase
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchedMangaViewModel @Inject constructor(
    private val useCase: SearchedMangaUseCase
): ViewModel() {

    init {
        getSearchedMangaList()
    }

    private val _searchedMangaListValue = MutableStateFlow(SearchedMangaListState())
    var searchedMangaListValue: StateFlow<SearchedMangaListState> = _searchedMangaListValue

    fun getSearchedMangaList() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase("one piece").collect{ responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val searchedMangaPagingData = SearchedMangaListState(searchedMangaList = responseState.data)
                        _searchedMangaListValue.value = searchedMangaPagingData
                    }
                    is ResponseState.Loading -> {
                        val searchedMangaListState = SearchedMangaListState(isLoading = true)
                        _searchedMangaListValue.value = searchedMangaListState
                    }
                    is ResponseState.Error -> {
                        val searchedMangaListState = SearchedMangaListState(error = responseState.message ?: "An Unexpected Error")
                        _searchedMangaListValue.value = searchedMangaListState
                    }
                }
            }
        }
    }

}