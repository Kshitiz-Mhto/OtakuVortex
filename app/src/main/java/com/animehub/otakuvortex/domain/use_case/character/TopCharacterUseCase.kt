package com.animehub.otakuvortex.domain.use_case.character

import com.animehub.otakuvortex.data.remote.dto.topcharacter.toTopCharacterModel
import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel
import com.animehub.otakuvortex.domain.repository.TopCharacterRepository
import com.animehub.otakuvortex.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopCharacterUseCase @Inject constructor(
    private val repo: TopCharacterRepository
) {

    operator fun invoke(page: Int): Flow<ResponseState<List<TopCharacterModel>>> = flow {
        try {
            emit(ResponseState.Loading())
            val topCharacters = repo.getTopCharacters(page).data.map {
                it.toTopCharacterModel()
            }
            emit(ResponseState.Success(topCharacters))
        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Unexcepted Error occured!!"))
        }catch (e: IOException){
            emit(ResponseState.Error(e.localizedMessage ?: "Internet Connection Error!!"))
        }
    }

}