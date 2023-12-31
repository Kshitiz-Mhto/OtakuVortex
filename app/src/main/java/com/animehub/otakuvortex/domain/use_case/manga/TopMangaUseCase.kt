package com.animehub.otakuvortex.domain.use_case.manga

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import com.animehub.otakuvortex.domain.repository.manga.MangaRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class TopMangaUseCase @Inject constructor(
    private val repo: MangaRepository
) {
    operator fun invoke(): Flow<ResponseState<PagingData<TopMangaData>>> = flow {
        try {
            emit(ResponseState.Loading())
            repo.getTopManga().cachedIn(CoroutineScope(coroutineContext)).collect{
                emit(ResponseState.Success(it))
            }
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }

}