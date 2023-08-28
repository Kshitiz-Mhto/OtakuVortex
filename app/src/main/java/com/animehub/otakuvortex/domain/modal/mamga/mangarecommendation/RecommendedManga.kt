package com.animehub.otakuvortex.domain.modal.mamga.mangarecommendation

import com.animehub.otakuvortex.data.remote.dto.manga.recommendation.Entry

data class RecommendedManga(
    val entry: List<Entry>,
    val content: String
)