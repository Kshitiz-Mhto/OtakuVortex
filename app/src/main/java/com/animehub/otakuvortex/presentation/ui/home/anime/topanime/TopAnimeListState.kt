package com.animehub.otakuvortex.presentation.ui.home.anime.topanime

import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData

data class TopAnimeListState(
    val isLoading: Boolean = false,
    val topAnimeList: List<TopAnimeData> = emptyList(),
    val error: String = ""
)
