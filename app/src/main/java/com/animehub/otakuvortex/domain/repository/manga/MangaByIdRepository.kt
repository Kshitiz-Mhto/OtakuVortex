package com.animehub.otakuvortex.domain.repository.manga

import com.animehub.otakuvortex.domain.modal.mamga.mangabyid.MangaByIdData

interface MangaByIdRepository {
    suspend fun getMangaById(mangaId: Int): MangaByIdData
}