package com.animehub.otakuvortex.domain.repository

import com.animehub.otakuvortex.domain.modal.characterbyid.CharacterByIdData

interface CharacterByIdRepository {
    suspend fun getCharacterById(characterId: Int): CharacterByIdData
}