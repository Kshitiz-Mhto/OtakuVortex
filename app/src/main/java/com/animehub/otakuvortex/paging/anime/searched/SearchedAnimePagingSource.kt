package com.animehub.otakuvortex.paging.anime.searched

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.anime.searchanime.toSearchedAnimeData
import com.animehub.otakuvortex.domain.modal.anime.searchanime.SearchedAnimeData
import javax.inject.Inject

class SearchedAnimePagingSource @Inject constructor(
    private val jikanApi: JikanApi,
    private var query: String
): PagingSource<Int, SearchedAnimeData>() {

    override fun getRefreshKey(state: PagingState<Int, SearchedAnimeData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchedAnimeData> {
        try {
            val position = params.key ?: 1
            val response = jikanApi.getSearchedAnime(query, position)
//            Log.i("data", response.data.toString())
            return LoadResult.Page(
                data = response.data.map {
                    it.toSearchedAnimeData()
                },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.pagination.last_visible_page) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}