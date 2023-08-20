package com.animehub.otakuvortex.domain.repository.anime

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getTopAnime(): Flow<PagingData<TopAnimeData>>

}