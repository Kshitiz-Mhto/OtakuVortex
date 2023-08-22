package com.animehub.otakuvortex.domain.use_case.character

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel
import com.animehub.otakuvortex.domain.repository.TopCharacterRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class TopCharacterUseCase @Inject constructor(
    private val repo: TopCharacterRepository
) {

    operator fun invoke(): Flow<ResponseState<PagingData<TopCharacterModel>>> = flow {
        try {
            emit(ResponseState.Loading())
            repo.getTopCharacters().cachedIn(CoroutineScope(coroutineContext)).collect{
                emit(ResponseState.Success(it))
            }
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }

}