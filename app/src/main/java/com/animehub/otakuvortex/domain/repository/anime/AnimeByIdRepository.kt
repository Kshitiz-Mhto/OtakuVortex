package com.animehub.otakuvortex.domain.repository.anime

import com.animehub.otakuvortex.domain.modal.anime.animebyid.AnimeByIdData

interface AnimeByIdRepository {
    suspend fun getAnimeById(animeId: Int): AnimeByIdData
}