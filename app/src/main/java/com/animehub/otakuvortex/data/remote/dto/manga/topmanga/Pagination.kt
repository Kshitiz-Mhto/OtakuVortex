package com.animehub.otakuvortex.data.remote.dto.manga.topmanga

data class Pagination(
    val current_page: Int,
    val has_next_page: Boolean,
    val items: Items,
    val last_visible_page: Int
)