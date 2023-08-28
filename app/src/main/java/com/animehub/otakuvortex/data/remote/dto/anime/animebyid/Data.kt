package com.animehub.otakuvortex.data.remote.dto.anime.animebyid

import com.animehub.otakuvortex.domain.modal.anime.animebyid.AnimeByIdData

data class Data(
    val aired: Aired,
    val airing: Boolean,
    val approved: Boolean,
    val background: Any,
    val broadcast: Broadcast,
    val demographics: List<Demographic>,
    val duration: String,
    val episodes: Int,
    val explicit_genres: List<Any>,
    val external: List<External>,
    val favorites: Int,
    val genres: List<Genre>,
    val images: Images,
    val licensors: List<Licensor>,
    val mal_id: Int,
    val members: Int,
    val popularity: Int,
    val producers: List<Producer>,
    val rank: Int,
    val rating: String,
    val relations: List<Relation>,
    val score: Double,
    val scored_by: Int,
    val season: String,
    val source: String,
    val status: String,
    val streaming: List<Streaming>,
    val studios: List<Studio>,
    val synopsis: String,
    val theme: Theme,
    val themes: List<ThemeX>,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val title_synonyms: List<String>,
    val titles: List<Title>,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int
)

fun Data.toAnimeByIdData(): AnimeByIdData{
    return AnimeByIdData(
        animeId = mal_id,
        imageUrl = images.jpg.large_image_url,
        trailerUrl = trailer.embed_url,
        title = title,
        numOfEpisode = episodes,
        status = status,
        duration = duration,
        rating = rating,
        score = score,
        scoredBy = scored_by,
        popularity = popularity,
        description = synopsis,
        licensors = licensors,
        studios = studios,
        genres = genres,
        externalSites = external
    )
}