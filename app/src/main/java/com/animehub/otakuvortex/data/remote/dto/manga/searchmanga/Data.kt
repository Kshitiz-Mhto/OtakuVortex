package com.animehub.otakuvortex.data.remote.dto.manga.searchmanga

import com.animehub.otakuvortex.domain.modal.mamga.searchmanga.SearchedMangaData

data class Data(
    val approved: Boolean,
    val authors: List<Author>,
    val background: String,
    val chapters: Int,
    val demographics: List<Demographic>,
    val explicit_genres: List<Any>,
    val favorites: Int,
    val genres: List<Genre>,
    val images: Images,
    val mal_id: Int,
    val members: Int,
    val popularity: Int,
    val published: Published,
    val publishing: Boolean,
    val rank: Int,
    val score: Double,
    val scored: Double,
    val scored_by: Int,
    val serializations: List<Serialization>,
    val status: String,
    val synopsis: String,
    val themes: List<Theme>,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val title_synonyms: List<String>,
    val titles: List<Title>,
    val type: String,
    val url: String,
    val volumes: Int
)

fun Data.toSearchedMangaData(): SearchedMangaData {
    return SearchedMangaData(
        mangaId = mal_id,
        imageUrl = images.jpg.large_image_url,
        title = title
    )
}