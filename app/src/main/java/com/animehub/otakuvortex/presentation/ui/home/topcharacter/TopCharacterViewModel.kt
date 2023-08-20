package com.animehub.otakuvortex.presentation.ui.home.topcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.character.TopCharacterUseCase
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopCharacterViewModel @Inject constructor(
    private val topCharacterUseCase: TopCharacterUseCase
): ViewModel() {

    init {
        getTopCharacter()
    }

    private val _topCharacterListValue = MutableStateFlow(TopCharacterListState())
    var topCharacterListValue : StateFlow<TopCharacterListState> = _topCharacterListValue

    fun getTopCharacter() = viewModelScope.launch(
      Dispatchers.IO){
        topCharacterUseCase().collect{
            when (it) {
                is ResponseState.Success -> {
                    _topCharacterListValue.value =
                        TopCharacterListState(topCharacterList = it.data)
                }
                is ResponseState.Loading -> {
                    _topCharacterListValue.value = TopCharacterListState(isLoading = true)
                }
                is ResponseState.Error -> {
                    _topCharacterListValue.value =
                        TopCharacterListState(error = it.message ?: "An Unexcepted Error")
                }
            }
        }

    }

}