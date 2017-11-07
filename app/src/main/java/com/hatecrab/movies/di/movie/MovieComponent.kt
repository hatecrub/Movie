package com.hatecrab.movies.di.movie

import com.hatecrab.movies.di.FragmentScope
import com.hatecrab.movies.ui.movie.view.MovieFragment
import com.hatecrab.movies.ui.movieslist.view.MoviesListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(MovieModule::class))
interface MovieComponent {
    fun inject(fragment: MoviesListFragment)
    fun inject(fragment: MovieFragment)
}