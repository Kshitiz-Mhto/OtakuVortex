package com.animehub.otakuvortex.data.remote

import com.animehub.otakuvortex.data.remote.dto.anime.topanime.TopAnimeDto
import com.animehub.otakuvortex.data.remote.dto.topcharacter.TopCharacterDto
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanApi {

    @GET("/v4/top/anime")
    suspend fun getTopAnime(@Query("page") page: Int): TopAnimeDto

    @GET("/v4/top/manga")
    suspend fun getTopManga(@Query("page") page: Int): TopMangaData

    @GET("/v4/top/characters")
    suspend fun getTopCharacters(@Query("page") page: Int): TopCharacterDto

}