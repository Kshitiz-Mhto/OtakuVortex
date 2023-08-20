package com.animehub.otakuvortex.domain.use_case.anime

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData
import com.animehub.otakuvortex.domain.repository.anime.AnimeRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopAnimeUseCase @Inject constructor(
    private val repo: AnimeRepository
) {
    operator fun invoke(): Flow<ResponseState<PagingData<TopAnimeData>>> = flow{
        try {
            emit(ResponseState.Loading())
            repo.getTopAnime().collect(){
//                Log.i("info",it.toString())
                emit(ResponseState.Success(it))
            }
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }

}