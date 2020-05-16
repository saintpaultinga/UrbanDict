package com.tsp.learn.android.urbandict.di

import com.tsp.learn.android.urbandict.SearchActivity
import dagger.Module

@Module
internal interface AppModule {
    fun inject(searchActivity: SearchActivity)
}