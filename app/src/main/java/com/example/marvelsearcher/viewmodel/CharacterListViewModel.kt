package com.example.marvelsearcher.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelsearcher.repository.CharacterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    init {
        searchCharacter()
    }

    fun searchCharacter() {
        viewModelScope.launch {
            characterRepository.getCharactersByName("spi")
        }
    }

    val characters = characterRepository.characters
}
