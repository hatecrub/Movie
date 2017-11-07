package com.hatecrab.movies.ui.movie.view

import com.arellomobile.mvp.MvpView
import com.hatecrab.movies.data.Genre
import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.data.MovieFullInfoResponse

interface IMovieView: MvpView {
    fun onMovieFullInfoLoaded(movieFullInfoResponse: MovieFullInfoResponse, genres: List<Genre>, similarMovies: List<Movie>)
    fun showProgress()
    fun hideProgress()
}