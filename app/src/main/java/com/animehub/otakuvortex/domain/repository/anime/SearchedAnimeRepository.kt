package com.animehub.otakuvortex.domain.repository.anime

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.anime.searchanime.SearchedAnimeData
import kotlinx.coroutines.flow.Flow

interface SearchedAnimeRepository {

    suspend fun getSearchedAnime(query: String): Flow<PagingData<SearchedAnimeData>>
}