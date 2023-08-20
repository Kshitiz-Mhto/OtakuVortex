package com.animehub.otakuvortex.paging.topcharacter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.topcharacter.toTopCharacterModel
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel
import javax.inject.Inject

class TopCharacterPagingSource@Inject constructor(
    private val jikanApi: JikanApi
): PagingSource<Int, TopCharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, TopCharacterModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopCharacterModel> {
        try {
            val position = params.key ?: 1
            val response = jikanApi.getTopCharacters(position)
            return LoadResult.Page(
                data = response.data.map {
                    it.toTopCharacterModel()
                },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.pagination.last_visible_page) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}