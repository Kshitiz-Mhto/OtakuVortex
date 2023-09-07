package com.animehub.otakuvortex.data.remote.repository.anime

import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.anime.animebyid.toAnimeByIdData
import com.animehub.otakuvortex.domain.modal.anime.animebyid.AnimeByIdData
import com.animehub.otakuvortex.domain.repository.anime.AnimeByIdRepository
import javax.inject.Inject

class AnimeByIdRepositoryImpl@Inject constructor(
    private val jikanApi: JikanApi
): AnimeByIdRepository {
    override suspend fun getAnimeById(animeId: Int): AnimeByIdData{
        val response = jikanApi.getAnimeById(animeId).data
        return response.toAnimeByIdData()
    }
}