package com.animehub.otakuvortex.domain.use_case.anime

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.animehub.otakuvortex.domain.modal.anime.searchanime.SearchedAnimeData
import com.animehub.otakuvortex.domain.repository.anime.SearchedAnimeRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class SearchedAnimeUseCase @Inject constructor(
    private val repo: SearchedAnimeRepository,
) {

    operator fun invoke(query: String): Flow<ResponseState<PagingData<SearchedAnimeData>>> = flow {
        try {
            emit(ResponseState.Loading())
            repo.getSearchedAnime(query).cachedIn(CoroutineScope(coroutineContext)).collect{
                emit(ResponseState.Success(it))
            }
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }
}