package com.example.marvelsearcher.di

import com.example.marvelsearcher.ui.ComicListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeComicListFragment(): ComicListFragment
}