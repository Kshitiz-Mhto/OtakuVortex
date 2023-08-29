package com.animehub.otakuvortex.presentation.ui.info.manga

import com.animehub.otakuvortex.domain.modal.mamga.mangabyid.MangaByIdData

data class MangaByIdListState(
    val isLoading: Boolean = false,
    val mangaById: MangaByIdData? = null,
    val error: String = ""
)