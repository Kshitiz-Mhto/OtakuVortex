package com.animehub.otakuvortex.domain.modal.mamga.mangabyid

import com.animehub.otakuvortex.data.remote.dto.manga.mangabyid.External
import com.animehub.otakuvortex.data.remote.dto.manga.mangabyid.Genre

data class MangaByIdData(
     val animeId: Int,
     val imageUrl: String,
     val title: String,
     val numOfChapters: Int,
     val status: String,
     val score: Double,
     val scoredBy: Int,
     val popularity: Int,
     val description: String,
     val background: String,
     val genres: List<Genre>,
     val externalSites: List<External>
)