package com.animehub.otakuvortex.domain.use_case.anime

import com.animehub.otakuvortex.data.remote.dto.anime.topanime.toTopAnimeData
import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData
import com.animehub.otakuvortex.domain.repository.anime.AnimeRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TopAnimeUseCase @Inject constructor(
    private val repo: AnimeRepository
) {

    operator fun invoke(page: Int): Flow<ResponseState<List<TopAnimeData>>> = flow{
        try {
            emit(ResponseState.Loading())
            val topAnimes = repo.getTopAnime(page).data.map {
                it.toTopAnimeData()
            }
            emit(ResponseState.Success(topAnimes))
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }

}