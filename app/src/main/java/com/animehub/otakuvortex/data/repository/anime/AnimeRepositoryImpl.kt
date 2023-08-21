package com.animehub.otakuvortex.data.repository.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.domain.repository.anime.AnimeRepository
import com.animehub.otakuvortex.paging.anime.TopAnimePagingSource
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val jikanApi: JikanApi
): AnimeRepository {

    override suspend fun getTopAnime() = Pager(
            config = PagingConfig(pageSize = 25, maxSize = 75),
            pagingSourceFactory = { TopAnimePagingSource(jikanApi=jikanApi) }
        ).flow
}