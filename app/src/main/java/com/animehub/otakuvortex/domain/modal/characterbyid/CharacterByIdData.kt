package com.animehub.otakuvortex.domain.modal.characterbyid

import com.animehub.otakuvortex.data.remote.dto.characterbyid.Anime
import com.animehub.otakuvortex.data.remote.dto.characterbyid.Manga

data class CharacterByIdData(
    val characterId: Int,
    val imageUrl: String,
    val title: String,
    val titleKanji: String,
    val favorites: Int,
    val about: String,
    val featuredAnime: List<Anime>,
    val featuredManga: List<Manga>
)
