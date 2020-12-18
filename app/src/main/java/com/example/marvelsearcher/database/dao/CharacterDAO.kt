package com.example.marvelsearcher.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelsearcher.database.entity.CharacterEntity

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM character")
    fun getAll(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Long): LiveData<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg characters: CharacterEntity)
}
