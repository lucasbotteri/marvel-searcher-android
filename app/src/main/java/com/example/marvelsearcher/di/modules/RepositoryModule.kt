package com.example.marvelsearcher.di.modules

import com.example.marvelsearcher.database.dao.CharacterDAO
import com.example.marvelsearcher.network.CharacterService
import com.example.marvelsearcher.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    // TODO Cant change this to a bind?
    @Singleton
    @Provides
    fun bindCharacterDepository(characterService: CharacterService, characterDAO: CharacterDAO): CharacterRepository{
        return CharacterRepository(characterService, characterDAO)
    }
}
