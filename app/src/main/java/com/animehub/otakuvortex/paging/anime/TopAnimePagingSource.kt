package com.animehub.otakuvortex.paging.anime

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.anime.topanime.toTopAnimeData
import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData
import javax.inject.Inject

class TopAnimePagingSource @Inject constructor(
    private val jikanApi: JikanApi
): PagingSource<Int, TopAnimeData>() {

    override fun getRefreshKey(state: PagingState<Int, TopAnimeData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopAnimeData> {
        try {
            val position = params.key ?: 1
            val response = jikanApi.getTopAnime(position)
//            Log.i("data", response.data.toString())
            return LoadResult.Page(
                data = response.data.map {
                    it.toTopAnimeData()
                },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.pagination.last_visible_page) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}