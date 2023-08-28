package com.animehub.otakuvortex.presentation.ui.search.searchmanga

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.mamga.searchmanga.SearchedMangaData

data class SearchedMangaListState(
    val isLoading: Boolean = false,
    val searchedMangaList: PagingData<SearchedMangaData>? = null,
    val error: String = ""
)