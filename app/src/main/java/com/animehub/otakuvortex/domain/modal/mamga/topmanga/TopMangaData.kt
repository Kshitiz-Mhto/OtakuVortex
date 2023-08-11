package com.animehub.otakuvortex.domain.modal.mamga.topmanga

import com.animehub.otakuvortex.data.remote.dto.manga.topmanga.Genre

data class TopMangaData(
    val mangaId: Int,
    val imageUrl: String,
    val title: String,
    val genres: List<Genre>,
    val score: Double,
    val scored_by: Int
    )