package com.example.marvelsearcher.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelsearcher.database.dao.CharacterDAO
import com.example.marvelsearcher.database.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 2, exportSchema = false)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDAO

}
