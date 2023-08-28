package com.animehub.otakuvortex.presentation.di

import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.repository.TopCharacterRepositoryImpl
import com.animehub.otakuvortex.data.repository.anime.AnimeRepositoryImpl
import com.animehub.otakuvortex.data.repository.anime.SearchedAnimeRepositoryImpl
import com.animehub.otakuvortex.data.repository.manga.MangaRepositoryImpl
import com.animehub.otakuvortex.data.repository.manga.SearchedMangaRepositoryImpl
import com.animehub.otakuvortex.domain.repository.TopCharacterRepository
import com.animehub.otakuvortex.domain.repository.anime.AnimeRepository
import com.animehub.otakuvortex.domain.repository.anime.SearchedAnimeRepository
import com.animehub.otakuvortex.domain.repository.manga.MangaRepository
import com.animehub.otakuvortex.domain.repository.manga.SearchedMangaRepository
import com.animehub.otakuvortex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJikanApi(): JikanApi{
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JikanApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(jikenApi: JikanApi): AnimeRepository{
        return AnimeRepositoryImpl(jikanApi = jikenApi)
    }

    @Provides
    @Singleton
    fun provideMangaRepository(jikenApi: JikanApi): MangaRepository {
        return MangaRepositoryImpl(jikanApi = jikenApi)
    }

    @Provides
    @Singleton
    fun provideTopCharacterRepository(jikenApi: JikanApi): TopCharacterRepository{
        return TopCharacterRepositoryImpl(jikanApi = jikenApi)
    }

    @Provides
    @Singleton
    fun provideSearchedAnimeRepository(jikenApi: JikanApi): SearchedAnimeRepository{
        return SearchedAnimeRepositoryImpl(jikanApi = jikenApi)
    }

    @Provides
    @Singleton
    fun provideSearchedMangaRepository(jikenApi: JikanApi): SearchedMangaRepository{
        return SearchedMangaRepositoryImpl(jikanApi = jikenApi)
    }

}