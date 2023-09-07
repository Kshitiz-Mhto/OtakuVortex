package com.animehub.otakuvortex.data.remote.repository.manga

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.domain.repository.manga.MangaRepository
import com.animehub.otakuvortex.paging.manga.TopMangaPagingSource
import javax.inject.Inject

class MangaRepositoryImpl@Inject constructor(
    private val jikanApi: JikanApi
): MangaRepository {
    override suspend fun getTopManga() = Pager(
    config = PagingConfig(pageSize = 15, maxSize = 75),
    pagingSourceFactory = { TopMangaPagingSource(jikanApi=jikanApi) }
    ).flow

}