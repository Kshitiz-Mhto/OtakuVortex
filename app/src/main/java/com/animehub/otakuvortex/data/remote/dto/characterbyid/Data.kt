package com.animehub.otakuvortex.data.remote.dto.characterbyid

import com.animehub.otakuvortex.domain.modal.characterbyid.CharacterByIdData

data class Data(
    val about: String,
    val anime: List<Anime>,
    val favorites: Int,
    val images: ImagesX,
    val mal_id: Int,
    val manga: List<Manga>,
    val name: String,
    val name_kanji: String,
    val nicknames: List<Any>,
    val url: String,
    val voices: List<Voice>
)

fun Data.toCharacterByIdData(): CharacterByIdData {
    return CharacterByIdData(
        characterId = mal_id,
        imageUrl = images.jpg.image_url,
        title = name,
        titleKanji = name_kanji,
        favorites = favorites,
        about = about,
    )
}
