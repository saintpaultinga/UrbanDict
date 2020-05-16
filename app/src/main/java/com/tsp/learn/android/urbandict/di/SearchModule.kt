package com.tsp.learn.android.urbandict.di

import com.tsp.learn.android.core.RestModule
import com.tsp.learn.android.core.service.SearchService
import com.tsp.learn.android.urbandict.SearchPresenter
import com.tsp.learn.android.urbandict.SearchViewState
import com.tsp.learn.android.urbandict.contract.SearchContract
import com.tsp.learn.android.urbandict.repository.SearchRepository
import com.tsp.learn.android.urbandict.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RestModule::class])
class SearchModule {

    @Provides
    @Singleton
    fun provideSearchPresenter(searchRepository: SearchRepository): SearchContract.Presenter =
        SearchPresenter(searchRepository)

    @Provides
    @Singleton
    fun provideSearchState(): SearchViewState = SearchViewState()

    @Provides
    @Singleton
    fun provideBookRepository(searchService: SearchService): SearchRepository = SearchRepositoryImpl(searchService)
}