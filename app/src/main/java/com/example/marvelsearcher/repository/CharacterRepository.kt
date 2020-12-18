package com.example.marvelsearcher.repository

import android.util.Log
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
            val charactersResponse = characterService.getCharactersByName(name).data.results.asDatabaseEntity()
            characterDAO.insertAll(*charactersResponse.toTypedArray())
        }
    }

    // TODO should i check if is in DB, and fetch character if not?
    suspend fun getCharacterById(characterId: Long): CharacterEntity {
        return withContext(Dispatchers.IO){
            return@withContext characterService.getCharactersById(characterId).data.results.first().asDatabaseEntity()
        }
    }


}
