package com.animehub.otakuvortex.di

import android.content.Context
import androidx.room.Room
import com.animehub.otakuvortex.data.local.database.OtakuVortexDB
import com.animehub.otakuvortex.data.local.repository.AnimeContentRepository
import com.animehub.otakuvortex.data.local.repository.CharacterContentRepository
import com.animehub.otakuvortex.data.local.repository.MangaContentRepository
import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.repository.CharacterByIdRepositoryImpl
import com.animehub.otakuvortex.data.remote.repository.TopCharacterRepositoryImpl
import com.animehub.otakuvortex.data.remote.repository.anime.AnimeByIdRepositoryImpl
import com.animehub.otakuvortex.data.remote.repository.anime.AnimeRepositoryImpl
import com.animehub.otakuvortex.data.remote.repository.anime.SearchedAnimeRepositoryImpl
import com.animehub.otakuvortex.data.remote.repository.manga.MangaByIdRepositoryImpl
import com.animehub.otakuvortex.data.remote.repository.manga.MangaRepositoryImpl
import com.animehub.otakuvortex.data.remote.repository.manga.SearchedMangaRepositoryImpl
import com.animehub.otakuvortex.domain.repository.CharacterByIdRepository
import com.animehub.otakuvortex.domain.repository.TopCharacterRepository
import com.animehub.otakuvortex.domain.repository.anime.AnimeByIdRepository
import com.animehub.otakuvortex.domain.repository.anime.AnimeRepository
import com.animehub.otakuvortex.domain.repository.anime.SearchedAnimeRepository
import com.animehub.otakuvortex.domain.repository.manga.MangaByIdRepository
import com.animehub.otakuvortex.domain.repository.manga.MangaRepository
import com.animehub.otakuvortex.domain.repository.manga.SearchedMangaRepository
import com.animehub.otakuvortex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAnimeById(jikenApi: JikanApi): AnimeByIdRepository{
        return AnimeByIdRepositoryImpl(jikanApi = jikenApi)
    }

    @Provides
    @Singleton
    fun provideMangaById(jikenApi: JikanApi): MangaByIdRepository{
        return MangaByIdRepositoryImpl(jikanApi = jikenApi)
    }

    @Provides
    @Singleton
    fun provideCharacterById(jikenApi: JikanApi): CharacterByIdRepository{
        return CharacterByIdRepositoryImpl(jikanApi = jikenApi)
    }

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): OtakuVortexDB {
        return Room.databaseBuilder(
            context.applicationContext,
            OtakuVortexDB::class.java,
            "otakuvortexDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(db: OtakuVortexDB): AnimeContentRepository {
        return db.animeContentRepository()
    }

    @Provides
    @Singleton
    fun provideMangaDao(db: OtakuVortexDB): MangaContentRepository {
        return db.mangaContentRepository()
    }

    @Provides
    @Singleton
    fun provideDao(db: OtakuVortexDB): CharacterContentRepository{
        return db.characterContentRepository()
    }

}