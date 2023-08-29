package com.animehub.otakuvortex.domain.use_case.manga

import com.animehub.otakuvortex.domain.modal.mamga.mangabyid.MangaByIdData
import com.animehub.otakuvortex.domain.repository.manga.MangaByIdRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MangaByIdUseCase@Inject constructor(
    private val repo: MangaByIdRepository
) {
    operator fun invoke(mangaId: Int): Flow<ResponseState<MangaByIdData>> = flow{
        try {
            emit(ResponseState.Loading())
            val response = repo.getMangaById(mangaId)
//                Log.i("info",it.toString())
            emit(ResponseState.Success(response))
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }
}