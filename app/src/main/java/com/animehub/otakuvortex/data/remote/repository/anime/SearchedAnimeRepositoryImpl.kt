package com.animehub.otakuvortex.data.remote.repository.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.domain.modal.anime.searchanime.SearchedAnimeData
import com.animehub.otakuvortex.domain.repository.anime.SearchedAnimeRepository
import com.animehub.otakuvortex.paging.anime.searched.SearchedAnimePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchedAnimeRepositoryImpl @Inject constructor(
    private val jikanApi: JikanApi
): SearchedAnimeRepository {
    override suspend fun getSearchedAnime(query: String): Flow<PagingData<SearchedAnimeData>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 75),
        pagingSourceFactory = { SearchedAnimePagingSource(jikanApi=jikanApi, query=query) }
        ).flow
}