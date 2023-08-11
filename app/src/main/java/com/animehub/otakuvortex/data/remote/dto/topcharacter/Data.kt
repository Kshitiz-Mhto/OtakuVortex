package com.animehub.otakuvortex.data.remote.dto.topcharacter

import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel

data class Data(
    val about: String,
    val favorites: Int,
    val images: Images,
    val mal_id: Int,
    val name: String,
    val name_kanji: String,
    val nicknames: List<String>,
    val url: String
)

fun Data.toTopCharacterModel():TopCharacterModel{
    return TopCharacterModel(
        charId = mal_id,
        name = name,
        nickKanji = name_kanji,
        imageUrl = images.jpg.image_url,
        about = about,
        favoriteBy = favorites
    )
}