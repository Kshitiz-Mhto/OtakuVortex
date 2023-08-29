package com.animehub.otakuvortex.presentation.ui.info.character

import com.animehub.otakuvortex.domain.modal.characterbyid.CharacterByIdData

data class CharacterByIdState(
    val isLoading: Boolean = false,
    val chracterById: CharacterByIdData? = null,
    val error: String = ""
)