package com.animehub.otakuvortex.data.repository.manga

import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.manga.topmanga.TopMangaDto
import com.animehub.otakuvortex.domain.repository.manga.MangaRepository
import javax.inject.Inject

class MangaRepositoryImpl@Inject constructor(
    private val jikanApi: JikanApi
): MangaRepository {

    override suspend fun getTopManga(page: Int): TopMangaDto {
        return jikanApi.getTopManga(page)
    }

}