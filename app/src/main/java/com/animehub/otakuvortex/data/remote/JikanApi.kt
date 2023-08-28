package com.animehub.otakuvortex.data.remote

import com.animehub.otakuvortex.data.remote.dto.anime.animebyid.AnimeByIdDto
import com.animehub.otakuvortex.data.remote.dto.anime.recommendation.AnimeRecommendationDto
import com.animehub.otakuvortex.data.remote.dto.anime.searchanime.SearchedAnimeDto
import com.animehub.otakuvortex.data.remote.dto.anime.topanime.TopAnimeDto
import com.animehub.otakuvortex.data.remote.dto.manga.mangabyid.MangaByIdDto
import com.animehub.otakuvortex.data.remote.dto.manga.recommendation.MangaRecommendationDto
import com.animehub.otakuvortex.data.remote.dto.manga.searchmanga.SearchedMangaDto
import com.animehub.otakuvortex.data.remote.dto.manga.topmanga.TopMangaDto
import com.animehub.otakuvortex.data.remote.dto.topcharacter.TopCharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApi {

    @GET("/v4/top/anime")
    suspend fun getTopAnime(@Query("page") page:Int = 1):TopAnimeDto

    @GET("/v4/top/manga")
    suspend fun getTopManga(@Query("page") page: Int = 1): TopMangaDto

    @GET("/v4/top/characters")
    suspend fun getTopCharacters(@Query("page") page: Int = 1): TopCharacterDto

    @GET("/v4/anime")
    suspend fun getSearchedAnime(@Query("q") query: String, @Query("page") page: Int = 1): SearchedAnimeDto

    @GET("/v4/manga")
    suspend fun getSearchedManga(@Query("q") query: String, @Query("page") page: Int = 1): SearchedMangaDto

    @GET("/v4/anime/{id}/full")
    suspend fun getAnimeById(@Path("id") animeId: Int): AnimeByIdDto

    @GET("/v4/manga/{id}/full")
    suspend fun getMangaById(@Path("id") mangaId: Int): MangaByIdDto

    @GET("/v4/recommendation/anime")
    suspend fun getAnimeRecommendation(): AnimeRecommendationDto

    @GET("/v4/recommendations/manga")
    suspend fun getMangaRecommendation(): MangaRecommendationDto

}