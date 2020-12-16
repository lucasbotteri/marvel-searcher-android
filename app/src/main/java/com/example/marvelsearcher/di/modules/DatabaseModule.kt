package com.example.marvelsearcher.di.modules

import android.app.Application
import androidx.room.Room
import com.example.marvelsearcher.database.MarvelDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule() {

    @Singleton
    @Provides
    fun providesMarvelDatabase(application: Application): MarvelDatabase = Room.databaseBuilder(application, MarvelDatabase::class.java, "marvel_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCharacterDAO(marvelDatabase : MarvelDatabase) = marvelDatabase.characterDao()

}
