package com.animehub.otakuvortex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.domain.repository.TopCharacterRepository
import com.animehub.otakuvortex.paging.topcharacter.TopCharacterPagingSource
import javax.inject.Inject

class TopCharacterRepositoryImpl@Inject constructor(
    private val jikanApi: JikanApi
): TopCharacterRepository {

    override suspend fun getTopCharacters() = Pager(
    config = PagingConfig(pageSize = 25, maxSize = 75),
    pagingSourceFactory = { TopCharacterPagingSource(jikanApi=jikanApi) }
    ).flow

}