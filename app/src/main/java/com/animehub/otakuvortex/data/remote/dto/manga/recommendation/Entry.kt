package com.animehub.otakuvortex.data.remote.dto.manga.recommendation

data class Entry(
    val images: Images,
    val mal_id: Int,
    val title: String,
    val url: String
)