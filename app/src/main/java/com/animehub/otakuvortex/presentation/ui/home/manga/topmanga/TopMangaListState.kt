package com.animehub.otakuvortex.presentation.ui.home.manga.topmanga

import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData

data class TopMangaListState(
    val isLoading: Boolean = false,
    val topMangaList: List<TopMangaData> = emptyList(),
    val error: String = ""
)