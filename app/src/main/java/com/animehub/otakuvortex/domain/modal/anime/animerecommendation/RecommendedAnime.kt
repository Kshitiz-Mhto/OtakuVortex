package com.animehub.otakuvortex.domain.modal.anime.animerecommendation

import com.animehub.otakuvortex.data.remote.dto.anime.recommendation.Entry

data class RecommendedAnime(
    val entry: List<Entry>,
    val content: String
)
