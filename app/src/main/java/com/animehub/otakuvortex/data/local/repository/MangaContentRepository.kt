package com.animehub.otakuvortex.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.animehub.otakuvortex.data.local.model.MangaContent

@Dao
interface MangaContentRepository {

    @Upsert
    suspend fun upsertFavorite(content: MangaContent? = null)

    @Delete
    suspend fun deleteFavorite(content: MangaContent? = null)

    @Query("SELECT * FROM mangacontent")
    suspend fun getAllFavoriteCotent(): List<MangaContent>
}