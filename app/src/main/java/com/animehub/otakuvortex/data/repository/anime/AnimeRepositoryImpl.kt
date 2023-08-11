package com.animehub.otakuvortex.data.repository.anime

import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.anime.topanime.TopAnimeDto
import com.animehub.otakuvortex.domain.repository.anime.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val jikanApi: JikanApi
): AnimeRepository {

    override suspend fun getTopAnime(page: Int): TopAnimeDto {
        return jikanApi.getTopAnime(page)
    }

}