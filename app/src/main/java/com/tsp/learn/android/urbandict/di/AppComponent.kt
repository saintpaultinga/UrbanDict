package com.tsp.learn.android.urbandict.di

import com.tsp.learn.android.urbandict.SearchActivity
import com.tsp.learn.android.urbandict.SearchApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, SearchModule::class])
interface AppComponent {

    fun inject(application: SearchActivity)
}