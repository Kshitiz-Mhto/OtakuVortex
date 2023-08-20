package com.animehub.otakuvortex.util

import androidx.paging.PagingData
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel
import kotlinx.coroutines.flow.Flow

sealed class ResponseState<T>(val data :T?=null,val message:String?=null){
    class Loading<T>(data:T?=null):ResponseState<T>(data)
    class Success<T>(data: T):ResponseState<T>(data)
    class Success1<T>(data: Flow<PagingData<TopCharacterModel>>):ResponseState<T>()
    class Success2<T>(data: Flow<PagingData<TopMangaData>>):ResponseState<T>()
    class Error<T>(message:String,data:T?=null):ResponseState<T>(data,message)
}
