package com.animehub.otakuvortex.data.remote.repository

import com.animehub.otakuvortex.data.remote.JikanApi
import com.animehub.otakuvortex.data.remote.dto.characterbyid.toCharacterByIdData
import com.animehub.otakuvortex.domain.modal.characterbyid.CharacterByIdData
import com.animehub.otakuvortex.domain.repository.CharacterByIdRepository
import javax.inject.Inject

class CharacterByIdRepositoryImpl@Inject constructor(
    private val jikanApi: JikanApi
): CharacterByIdRepository {
    override suspend fun getCharacterById(characterId: Int): CharacterByIdData {
        val response = jikanApi.getCharacterById(characterId = characterId).data
        return response.toCharacterByIdData()
    }
}