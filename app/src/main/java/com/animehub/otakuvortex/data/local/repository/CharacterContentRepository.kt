package com.animehub.otakuvortex.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.animehub.otakuvortex.data.local.model.CharacterContent

@Dao
interface CharacterContentRepository {
    @Upsert
    suspend fun upsertFavorite(content: CharacterContent? = null)

    @Delete
    suspend fun deleteFavorite(content: CharacterContent? = null)

    @Query("SELECT * FROM charcontent")
    suspend fun getAllFavoriteCotent(): List<CharacterContent>
}