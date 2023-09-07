package com.animehub.otakuvortex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "charcontent")
data class CharacterContent(
    @PrimaryKey(autoGenerate = false)
    val id: Int
)
