package com.tsp.learn.android.urbandict

import android.app.Application
import com.tsp.learn.android.core.RestModule
import com.tsp.learn.android.urbandict.di.AppComponent
import com.tsp.learn.android.urbandict.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree


class SearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
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