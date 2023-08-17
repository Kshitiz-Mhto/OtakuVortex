package com.animehub.otakuvortex.domain.use_case.manga

import com.animehub.otakuvortex.data.remote.dto.manga.topmanga.toTopMangaData
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import com.animehub.otakuvortex.domain.repository.manga.MangaRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopMangaUseCase @Inject constructor(
    private val repo: MangaRepository
) {

    operator fun invoke(page: Int): Flow<ResponseState<List<TopMangaData>>> = flow {
        try {

            emit(ResponseState.Loading())
            val topMangas = repo.getTopManga(page).data.map {
                it.toTopMangaData()
            }
            emit(ResponseState.Success(topMangas))
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }

}