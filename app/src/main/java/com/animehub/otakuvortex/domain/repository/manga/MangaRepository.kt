package com.animehub.otakuvortex.domain.repository.manga

import com.animehub.otakuvortex.data.remote.dto.manga.topmanga.TopMangaDto

interface MangaRepository {

    suspend fun getTopManga(page: Int): TopMangaDto

}