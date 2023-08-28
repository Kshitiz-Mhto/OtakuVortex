package com.animehub.otakuvortex.data.remote

import com.animehub.otakuvortex.data.remote.dto.anime.searchanime.SearchedAnimeDto
import com.animehub.otakuvortex.data.remote.dto.anime.topanime.TopAnimeDto
import com.animehub.otakuvortex.data.remote.dto.manga.searchmanga.SearchedMangaDto
import com.animehub.otakuvortex.data.remote.dto.manga.topmanga.TopMangaDto
import com.animehub.otakuvortex.data.remote.dto.topcharacter.TopCharacterDto
import retrofit2.http.GET
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

}