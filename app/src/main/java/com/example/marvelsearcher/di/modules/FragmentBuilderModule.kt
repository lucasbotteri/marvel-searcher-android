package com.example.marvelsearcher.di.modules

import com.example.marvelsearcher.ui.CharacterDetails
import com.example.marvelsearcher.ui.CharacterList
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeComicListFragment(): CharacterList

    @ContributesAndroidInjector
    abstract fun contributeComicDetailFragment(): CharacterDetails
}
