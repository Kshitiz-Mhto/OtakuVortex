package com.animehub.otakuvortex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animecontent")
data class AnimeContent(
    @PrimaryKey(autoGenerate = false)
    val id: Int
)
