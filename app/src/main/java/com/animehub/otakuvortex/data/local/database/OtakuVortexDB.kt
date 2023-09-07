package com.animehub.otakuvortex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.animehub.otakuvortex.data.local.model.AnimeContent
import com.animehub.otakuvortex.data.local.model.CharacterContent
import com.animehub.otakuvortex.data.local.model.MangaContent
import com.animehub.otakuvortex.data.local.repository.AnimeContentRepository
import com.animehub.otakuvortex.data.local.repository.CharacterContentRepository
import com.animehub.otakuvortex.data.local.repository.MangaContentRepository

@Database(entities = [AnimeContent::class, MangaContent::class, CharacterContent::class], version = 1)
abstract class OtakuVortexDB: RoomDatabase() {

    abstract fun animeContentRepository(): AnimeContentRepository
    abstract fun mangaContentRepository(): MangaContentRepository
    abstract fun characterContentRepository(): CharacterContentRepository

}