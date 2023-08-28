package com.animehub.otakuvortex.data.repository.manga

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.searchmanga.SearchedMangaData
import com.animehub.otakuvortex.domain.repository.manga.SearchedMangaRepository
import com.animehub.otakuvortex.paging.manga.searched.SearchedMangaPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchedMangaRepositoryImpl @Inject constructor(
    private val jikanApi: JikanApi
): SearchedMangaRepository {
    override suspend fun getSearchedManga(query: String): Flow<PagingData<SearchedMangaData>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 75),
        pagingSourceFactory = {SearchedMangaPagingSource(jikanApi, query)}
    ).flow
}