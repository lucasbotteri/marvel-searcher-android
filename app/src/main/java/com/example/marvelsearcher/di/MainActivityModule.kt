package com.example.marvelsearcher.di

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