package com.animehub.otakuvortex.paging.manga

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.manga.topmanga.toTopMangaData
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import javax.inject.Inject

class TopMangaPagingSource@Inject constructor(
    private val jikanApi: JikanApi
): PagingSource<Int, TopMangaData>() {
    override fun getRefreshKey(state: PagingState<Int, TopMangaData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopMangaData> {
        try {
            val position = params.key ?: 1
            val response = jikanApi.getTopManga(position)
            return LoadResult.Page(
                data = response.data.map {
                    it.toTopMangaData()
                },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.pagination.last_visible_page) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}