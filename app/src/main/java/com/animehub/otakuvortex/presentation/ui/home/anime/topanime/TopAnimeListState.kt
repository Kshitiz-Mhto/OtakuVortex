package com.animehub.otakuvortex.presentation.ui.home.anime.topanime

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData

data class TopAnimeListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val topAnimeList: PagingData<TopAnimeData>? = null
)
