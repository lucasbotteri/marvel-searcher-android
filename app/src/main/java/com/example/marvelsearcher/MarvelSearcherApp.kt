package com.example.marvelsearcher

import com.example.marvelsearcher.di.AppComponent
import com.example.marvelsearcher.di.DaggerAppComponent
import dagger.android.DaggerApplication

class MarvelSearcherApp: DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AppComponent = applicationInjector
}