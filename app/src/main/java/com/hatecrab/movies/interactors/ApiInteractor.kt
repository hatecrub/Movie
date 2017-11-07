package com.hatecrab.movies.interactors

import com.hatecrab.movies.api.IRemoteApi

class ApiInteractor(private val api: IRemoteApi) {
    fun loadMovies(pageNumber: Int = 0) = api.getTopRatedMovies(pageNumber)
    fun loadGenres() = api.getGenresList()
    fun loadMovieFullInfo(id: Int) = api.getMovieFullInfo(id)
    fun loadSimilarMovies(id: Int) = api.getSimilarMovies(id)
}
