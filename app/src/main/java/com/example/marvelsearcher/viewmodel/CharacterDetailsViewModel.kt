package com.example.marvelsearcher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelsearcher.database.entity.CharacterEntity
import com.example.marvelsearcher.repository.CharacterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _character = MutableLiveData<CharacterEntity>()
    val character: LiveData<CharacterEntity>
    get() = _character


    fun getCharacter(characterId: Long) {
        viewModelScope.launch {
            // TODO i only need this binding to use the description
            _character.value = characterRepository.getCharacterById(characterId)
        }

    }


}
