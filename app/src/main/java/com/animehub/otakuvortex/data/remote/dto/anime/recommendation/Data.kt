package com.animehub.otakuvortex.data.remote.dto.anime.recommendation

import com.animehub.otakuvortex.domain.modal.anime.animerecommendation.RecommendedAnime

data class Data(
    val content: String,
    val date: String,
    val entry: List<Entry>,
    val mal_id: String,
    val user: User
)

fun Data.toRecommendedAnime(): RecommendedAnime{
    return RecommendedAnime(
        entry = entry,
        content = content
    )
}