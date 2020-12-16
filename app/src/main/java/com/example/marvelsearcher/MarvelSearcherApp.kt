package com.example.marvelsearcher

import android.content.Context
import androidx.multidex.MultiDex
import com.example.marvelsearcher.config.Constant
import com.example.marvelsearcher.di.AppComponent
import com.example.marvelsearcher.di.DaggerAppComponent
import dagger.android.DaggerApplication

class MarvelSearcherApp: DaggerApplication() {
    private val applicationInjector =
        DaggerAppComponent.builder()
        .application(this)
        .marvelBaseUrl(Constant.MARVEL_BASE_URL)
        .marvelApiKey(Constant.MARVEL_API_KEY)
        .build()

    override fun applicationInjector(): AppComponent = applicationInjector

    // TODO i don't think i should do this
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
