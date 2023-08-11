package com.animehub.otakuvortex.domain.repository.anime

import com.animehub.otakuvortex.data.remote.dto.anime.topanime.TopAnimeDto

interface AnimeRepository {

    suspend fun getTopAnime(page: Int): TopAnimeDto


}