package com.example.marvelsearcher.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.marvelsearcher.database.entity.CharacterEntity

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM character")
    fun getAll(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Long): LiveData<List<CharacterEntity>>

    @Query("DELETE FROM character")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg characters: CharacterEntity)

}
