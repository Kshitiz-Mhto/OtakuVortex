package com.animehub.otakuvortex.domain.modal.anime.animebyid

import com.animehub.otakuvortex.data.remote.dto.anime.animebyid.External
import com.animehub.otakuvortex.data.remote.dto.anime.animebyid.Genre
import com.animehub.otakuvortex.data.remote.dto.anime.animebyid.Studio
import com.animehub.otakuvortex.data.remote.dto.anime.animebyid.Licensor

data class AnimeByIdData(
    val animeId: Int,
    val imageUrl: String,
    val title: String,
    val numOfEpisode: Int,
    val status: String,
    val duration: String,
    val rating: String,
    val score: Double,
    val scoredBy: Int,
    val popularity: Int,
    val description: String,
    val licensors: List<Licensor>,
    val studios: List<Studio>,
    val genres: List<Genre>,
    val externalSites: List<External>
)