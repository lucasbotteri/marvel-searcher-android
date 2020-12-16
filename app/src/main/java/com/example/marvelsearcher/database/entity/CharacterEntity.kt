package com.example.marvelsearcher.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey var id: Long,
    var name: String,
    @ColumnInfo(name = "image_path") var imagePath: String ?
    )
