package com.hatecrab.movies.ui.movie.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.hatecrab.movies.data.*
import com.hatecrab.movies.interactors.ApiInteractor
import com.hatecrab.movies.ui.common.BasePresenter
import com.hatecrab.movies.ui.movie.view.IMovieView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

@InjectViewState
class MoviePresenter(private val apiInteractor: ApiInteractor) : BasePresenter<IMovieView>() {

    private lateinit var movie: Movie
    var movieFullInfo: MovieFullInfo? = null

    fun loadMovieFullInfo() {
        if (movieFullInfo == null) {
            Observable.zip(
                    apiInteractor.loadMovieFullInfo(movie.id),
                    apiInteractor.loadGenres(),
                    apiInteractor.loadSimilarMovies(movie.id),
                    { movieFullInfoResponse, genreListResponse, similarFilmsResponse ->
                        MovieFullInfo(movieFullInfoResponse, genreListResponse, similarFilmsResponse)
                    }
            )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { viewState.showProgress() }
                    .doAfterTerminate { viewState.hideProgress() }
                    .subscribe({
                        movieFullInfo = it
                        val genres = mutableListOf<Genre>()
                        val allGenres = it.genresResponse.genres
                        movie.genreIds.forEach { genreId ->
                            allGenres.find { it.id == genreId }?.let { genres.add(it) }
                        }
                        viewState.onMovieFullInfoLoaded(it.movieFullInfoResponse, genres, it.similarFilmsResponse.results)
                    }, {
                        Timber.e(it)
                    }).unsubscribeOnDestroy()
        }
    }

    data class MovieFullInfo(val movieFullInfoResponse: MovieFullInfoResponse, val genresResponse: GenreListResponse,
                             val similarFilmsResponse: MovieListResponse)

    fun setMovie(movie: Movie) {
        this.movie = movie
    }
}
