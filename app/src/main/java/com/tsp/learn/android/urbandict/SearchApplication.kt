package com.tsp.learn.android.urbandict

import android.app.Application
import com.tsp.learn.android.core.RestModule
import com.tsp.learn.android.urbandict.di.AppComponent
import com.tsp.learn.android.urbandict.di.DaggerAppComponent

class SearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        appComponent = DaggerAppComponent.builder()
            .restModule(RestModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}