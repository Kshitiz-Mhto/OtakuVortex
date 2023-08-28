package com.animehub.otakuvortex.data.remote.dto.manga.recommendation

import com.animehub.otakuvortex.domain.modal.mamga.mangarecommendation.RecommendedManga

data class Data(
    val content: String,
    val date: String,
    val entry: List<Entry>,
    val mal_id: String,
    val user: User
)

fun Data.toReoommendedManga(): RecommendedManga{
    return RecommendedManga(
        entry = entry,
        content = content
    )
}