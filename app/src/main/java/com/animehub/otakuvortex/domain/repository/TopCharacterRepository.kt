package com.animehub.otakuvortex.domain.repository

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel
import kotlinx.coroutines.flow.Flow

interface TopCharacterRepository {

    suspend fun getTopCharacters(): Flow<PagingData<TopCharacterModel>>
}