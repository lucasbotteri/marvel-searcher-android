package com.example.marvelsearcher.di.modules

import dagger.Module

@Module(includes = [
    ViewModelModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    RepositoryModule::class
])
class AppModule {
}
