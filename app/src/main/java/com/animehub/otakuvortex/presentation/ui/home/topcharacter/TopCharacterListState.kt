package com.animehub.otakuvortex.presentation.ui.home.topcharacter

import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel

data class TopCharacterListState(
    val isLoading: Boolean = false,
    val topCharacterList: List<TopCharacterModel> = emptyList(),
    val error: String = ""
)
