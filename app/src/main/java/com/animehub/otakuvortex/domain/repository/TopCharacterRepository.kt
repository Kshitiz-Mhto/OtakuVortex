package com.animehub.otakuvortex.domain.repository

import com.animehub.otakuvortex.data.remote.dto.topcharacter.TopCharacterDto

interface TopCharacterRepository {

    suspend fun getTopCharacters(page: Int): TopCharacterDto

}