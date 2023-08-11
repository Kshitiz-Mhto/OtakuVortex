package com.animehub.otakuvortex.domain.modal.anime.topanime

import com.animehub.otakuvortex.data.remote.dto.anime.topanime.Genre

data class TopAnimeData(
    val animeId: Int,
    val imageUrl: String,
    val genres: List<Genre>,
    val title: String,
    val score: Double,
    val scored_by: Int
)