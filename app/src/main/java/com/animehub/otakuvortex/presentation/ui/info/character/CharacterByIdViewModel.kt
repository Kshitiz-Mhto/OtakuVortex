package com.animehub.otakuvortex.presentation.ui.info.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animehub.otakuvortex.domain.use_case.character.CharacterByIdUseCase
import com.animehub.otakuvortex.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterByIdViewModel@Inject constructor(
    private val useCase: CharacterByIdUseCase
): ViewModel() {

    private val _charcterByIdListValue = MutableStateFlow(CharacterByIdState())
    var characterByIdListValue: StateFlow<CharacterByIdState> = _charcterByIdListValue

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase(characterId).collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val characterByIdValue = CharacterByIdState(chracterById = responseState.data)
                        _charcterByIdListValue.value = characterByIdValue
                    }
                    is ResponseState.Loading -> {
                        val characterByIdValue = CharacterByIdState(isLoading = true)
                        _charcterByIdListValue.value = characterByIdValue
                    }
                    is ResponseState.Error -> {
                        val characterByIdValue = CharacterByIdState(error = responseState.message ?: "An Unexpected Error")
                        _charcterByIdListValue.value = characterByIdValue
                    }
                }
            }
        }
    }
}