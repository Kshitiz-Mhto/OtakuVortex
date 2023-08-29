package com.animehub.otakuvortex.domain.use_case.character

import com.animehub.otakuvortex.domain.modal.characterbyid.CharacterByIdData
import com.animehub.otakuvortex.domain.repository.CharacterByIdRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterByIdUseCase@Inject constructor(
    private val repo: CharacterByIdRepository
) {

    operator fun invoke(charcterId: Int): Flow<ResponseState<CharacterByIdData>> = flow{
        try {
            emit(ResponseState.Loading())
            val response = repo.getCharacterById(characterId = charcterId)
            emit(ResponseState.Success(response))
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }

}