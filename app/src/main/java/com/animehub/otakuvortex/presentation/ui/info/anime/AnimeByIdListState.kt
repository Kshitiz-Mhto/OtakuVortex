package com.animehub.otakuvortex.presentation.ui.info.anime

import com.animehub.otakuvortex.domain.modal.anime.animebyid.AnimeByIdData

data class AnimeByIdListState(
    val isLoading: Boolean = false,
    val animeById: AnimeByIdData? = null,
    val error: String = ""
)