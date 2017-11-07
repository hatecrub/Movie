package com.hatecrab.movies

import android.app.Application
import com.hatecrab.movies.di.ApplicationComponent
import com.hatecrab.movies.di.DaggerApplicationComponent
import com.hatecrab.movies.di.utilites.AndroidModule
import timber.log.Timber

class MobileApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }


    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())
    }
}