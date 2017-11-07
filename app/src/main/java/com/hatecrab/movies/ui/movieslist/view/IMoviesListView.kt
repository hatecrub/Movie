package com.hatecrab.movies.ui.movieslist.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.hatecrab.movies.ui.common.items.MovieItem

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMoviesListView : MvpView {
    fun showError(errorMessage: CharSequence?)
    fun showProgress()

    @StateStrategyType(AddToEndStrategy::class)
    fun addMovies(movies: List<MovieItem>)
}