package com.animehub.otakuvortex.presentation.ui.search.searchanime

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.anime.searchanime.SearchedAnimeData

data class SearchedAnimeListState (
    val isLoading: Boolean = false,
    val searchedAnimeList: PagingData<SearchedAnimeData>? = null,
    val error: String = ""
)