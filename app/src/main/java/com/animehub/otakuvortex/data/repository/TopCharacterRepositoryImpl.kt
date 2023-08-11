package com.animehub.otakuvortex.data.repository

import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.topcharacter.TopCharacterDto
import com.animehub.otakuvortex.domain.repository.TopCharacterRepository
import javax.inject.Inject

class TopCharacterRepositoryImpl@Inject constructor(
    private val jikanApi: JikanApi
): TopCharacterRepository {

    override suspend fun getTopCharacters(page: Int): TopCharacterDto {
        return jikanApi.getTopCharacters(page)
    }

}