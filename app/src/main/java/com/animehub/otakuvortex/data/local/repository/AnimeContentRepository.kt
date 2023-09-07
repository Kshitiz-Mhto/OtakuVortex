package com.animehub.otakuvortex.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.animehub.otakuvortex.data.local.model.AnimeContent

@Dao
interface AnimeContentRepository {

    @Upsert
    suspend fun upsertFavorite(content: AnimeContent? = null)

    @Delete
    suspend fun deleteFavorite(content: AnimeContent? = null)

    @Query("SELECT * FROM animecontent")
    suspend fun getAllFavoriteCotent(): List<AnimeContent>


}