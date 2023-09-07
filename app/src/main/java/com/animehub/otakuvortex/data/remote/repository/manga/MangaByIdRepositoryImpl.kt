package com.animehub.otakuvortex.data.remote.repository.manga

import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.manga.mangabyid.toMangaByIdData
import com.animehub.otakuvortex.domain.modal.mamga.mangabyid.MangaByIdData
import com.animehub.otakuvortex.domain.repository.manga.MangaByIdRepository
import javax.inject.Inject

class MangaByIdRepositoryImpl@Inject constructor(
    private val jikanApi: JikanApi
): MangaByIdRepository {
    override suspend fun getMangaById(mangaId: Int): MangaByIdData {
        val response = jikanApi.getMangaById(mangaId).data
        return response.toMangaByIdData()
    }
}