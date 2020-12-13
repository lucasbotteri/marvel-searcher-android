package com.example.marvelsearcher.di.modules

import com.example.marvelsearcher.ui.ComicDetails
import com.example.marvelsearcher.ui.ComicList
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeComicListFragment(): ComicList

    @ContributesAndroidInjector
    abstract fun contributeComicDetailFragment(): ComicDetails
}
