package com.animehub.otakuvortex.domain.repository.manga

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getTopManga(): Flow<PagingData<TopMangaData>>
}