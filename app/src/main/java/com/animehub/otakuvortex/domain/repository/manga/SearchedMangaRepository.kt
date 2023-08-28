package com.animehub.otakuvortex.domain.repository.manga

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.searchmanga.SearchedMangaData
import kotlinx.coroutines.flow.Flow

interface SearchedMangaRepository {

    suspend fun getSearchedManga(query: String): Flow<PagingData<SearchedMangaData>>
}