package com.animehub.otakuvortex.domain.use_case.anime

import com.animehub.otakuvortex.domain.modal.anime.animebyid.AnimeByIdData
import com.animehub.otakuvortex.domain.repository.anime.AnimeByIdRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnimeByIdUseCase@Inject constructor(
    private val repo: AnimeByIdRepository
) {
    operator fun invoke(animeId: Int): Flow<ResponseState<AnimeByIdData>> = flow{
        try {
            emit(ResponseState.Loading())
            val response = repo.getAnimeById(animeId)
//                Log.i("info",it.toString())
            emit(ResponseState.Success(response))
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }
}