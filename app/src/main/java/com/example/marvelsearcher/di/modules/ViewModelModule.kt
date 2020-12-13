package com.example.marvelsearcher.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelsearcher.di.keys.ViewModelKey
import com.example.marvelsearcher.viewmodel.ComicDetailsViewModel
import com.example.marvelsearcher.viewmodel.ComicListViewModel
import com.example.marvelsearcher.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ComicListViewModel::class)
    abstract fun bindComicListViewModel(ComicListViewModel: ComicListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ComicDetailsViewModel::class)
    abstract fun bindComicDetailsViewModel(ComicDetailsViewModel: ComicDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

