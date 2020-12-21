package com.example.marvelsearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelsearcher.repository.CharacterRepository
import com.example.marvelsearcher.util.LoadingState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    val charactersListLoadingState = MutableLiveData(LoadingState.NO_CONTENT)

    fun searchCharactersByName(name: String) {
        charactersListLoadingState.value = LoadingState.LOADING
        viewModelScope.launch {
            characterRepository.getCharactersByName(name)
            charactersListLoadingState.value = LoadingState.LOADED
        }
    }

    val characters = characterRepository.characters
}
