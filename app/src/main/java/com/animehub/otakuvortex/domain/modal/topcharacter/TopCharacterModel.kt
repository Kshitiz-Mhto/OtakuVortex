package com.animehub.otakuvortex.domain.modal.topcharacter

data class TopCharacterModel(
    val charId: Int,
    val imageUrl: String,
    val name: String,
    val nickKanji: String,
    val about: String,
    val favoriteBy: Int
    )