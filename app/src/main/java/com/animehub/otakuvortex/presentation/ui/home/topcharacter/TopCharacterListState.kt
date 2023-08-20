package com.animehub.otakuvortex.presentation.ui.home.topcharacter

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel

data class TopCharacterListState(
    val isLoading: Boolean = false,
    val topCharacterList: PagingData<TopCharacterModel>? = null,
    val error: String = ""
)
