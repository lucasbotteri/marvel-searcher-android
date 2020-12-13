package com.example.marvelsearcher.di.modules

import com.example.marvelsearcher.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(
            modules = [
                FragmentBuilderModule::class
            ]
    )

    abstract fun contributeMainActivity(): MainActivity
}