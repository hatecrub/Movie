package com.hatecrab.movies.di.activity

import com.hatecrab.movies.di.movie.MovieModule
import com.hatecrab.movies.MainActivity
import com.hatecrab.movies.di.movie.MovieComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun plus(module: MovieModule): MovieComponent

    fun inject(activity: MainActivity)
}
