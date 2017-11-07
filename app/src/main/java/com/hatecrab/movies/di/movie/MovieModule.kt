package com.hatecrab.movies.di.movie

import com.hatecrab.movies.di.FragmentScope
import com.hatecrab.movies.interactors.ApiInteractor
import com.hatecrab.movies.ui.movie.presenter.MoviePresenter
import com.hatecrab.movies.ui.movieslist.presenter.MoviesListPresenter
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @Provides
    @FragmentScope
    internal fun provideMoviesPresenter(apiInteractor: ApiInteractor): MoviesListPresenter {
        return MoviesListPresenter(apiInteractor)
    }

    @Provides
    @FragmentScope
    internal fun provideMoviePresenter(apiInteractor: ApiInteractor): MoviePresenter {
        return MoviePresenter(apiInteractor)
    }
}