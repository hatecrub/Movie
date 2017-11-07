package com.hatecrab.movies.di.utilites

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return application.applicationContext
    }
}
