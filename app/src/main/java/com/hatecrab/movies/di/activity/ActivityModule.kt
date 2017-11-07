package com.hatecrab.movies.di.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.hatecrab.movies.utils.Router
import com.hatecrab.movies.utils.UiCalculator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    @ActivityScope
    internal fun provideUiCalculator(context: Context): UiCalculator {
        return UiCalculator(context)
    }

    @Provides
    @ActivityScope
    internal fun provideRouter(activity: AppCompatActivity): Router {
        return Router(activity)
    }

}
