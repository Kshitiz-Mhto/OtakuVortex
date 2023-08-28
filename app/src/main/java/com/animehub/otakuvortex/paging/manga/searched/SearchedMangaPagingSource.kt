package com.animehub.otakuvortex.paging.manga.searched

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.manga.searchmanga.toSearchedMangaData
import com.animehub.otakuvortex.domain.modal.mamga.searchmanga.SearchedMangaData
import javax.inject.Inject

class SearchedMangaPagingSource@Inject constructor(
    private val jikanApi: JikanApi,
    private var query: String
): PagingSource<Int, SearchedMangaData>() {

    override fun getRefreshKey(state: PagingState<Int, SearchedMangaData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchedMangaData> {
        try {
            val position = params.key ?: 1
            val response = jikanApi.getSearchedManga(query, position)
//            Log.i("data", response.data.toString())
            return LoadResult.Page(
                data = response.data.map {
                    it.toSearchedMangaData()
                },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.pagination.last_visible_page) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}