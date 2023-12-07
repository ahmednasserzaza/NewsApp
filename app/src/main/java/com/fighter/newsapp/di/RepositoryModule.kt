package com.fighter.newsapp.di

import com.fighter.newsapp.data.remote.repository.NewsRepository
import com.fighter.newsapp.data.remote.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindNewsRepository(newsRepositoryImp: NewsRepositoryImpl): NewsRepository

}