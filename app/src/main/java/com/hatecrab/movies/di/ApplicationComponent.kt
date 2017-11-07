package com.hatecrab.movies.di

import com.hatecrab.movies.MobileApplication
import com.hatecrab.movies.di.activity.ActivityComponent
import com.hatecrab.movies.di.activity.ActivityModule
import com.hatecrab.movies.di.utilites.AndroidModule
import com.hatecrab.movies.di.utilites.ApiModule
import com.hatecrab.movies.di.utilites.InteractorsModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidModule::class, ApiModule::class, InteractorsModule::class))
interface ApplicationComponent {
    fun plus(activityModule: ActivityModule): ActivityComponent
    fun inject(app: MobileApplication)
}