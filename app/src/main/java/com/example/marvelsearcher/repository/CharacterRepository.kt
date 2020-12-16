package com.example.marvelsearcher.repository

import androidx.lifecycle.LiveData
import com.example.marvelsearcher.database.dao.CharacterDAO
import com.example.marvelsearcher.database.entity.CharacterEntity
import com.example.marvelsearcher.network.CharacterService
import com.example.marvelsearcher.network.dto.asDatabaseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepository @Inject constructor (private val characterService: CharacterService, private val characterDAO: CharacterDAO ) {

    val characters: LiveData<List<CharacterEntity>> = characterDAO.getAll()

    suspend fun getCharactersByName(name: String) {
        // TODO Handle error responses
        withContext(Dispatchers.IO){
            val charactersResponse = characterService.getCharactersByName(name)
            characterDAO.insertAll(*charactersResponse.data.results.asDatabaseEntity().toTypedArray())
        }
    }
}
