package com.example.marvelsearcher.di

import android.app.Application
import com.example.marvelsearcher.MarvelSearcherApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            MainActivityModule::class
        ]
)
interface AppComponent : AndroidInjector<MarvelSearcherApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MarvelSearcherApp)
}