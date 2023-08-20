package com.animehub.otakuvortex.presentation.ui.home.manga.topmanga

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData

data class TopMangaListState(
    val isLoading: Boolean = false,
    val topMangaList: PagingData<TopMangaData>? = null,
    val error: String = ""
)