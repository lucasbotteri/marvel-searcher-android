package com.example.marvelsearcher.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelsearcher.database.entity.CharacterEntity

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM character")
    fun getAll(): LiveData<List<CharacterEntity>>

    @Insert
    fun insertAll(vararg characters: CharacterEntity)
}
