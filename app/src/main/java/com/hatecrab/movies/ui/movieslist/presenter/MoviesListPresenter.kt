package com.hatecrab.movies.ui.movieslist.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.hatecrab.movies.interactors.ApiInteractor
import com.hatecrab.movies.ui.common.items.MovieItem
import com.hatecrab.movies.ui.movieslist.view.IMoviesListView
import com.hatecrab.movies.utils.Paginator
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

@InjectViewState
class MoviesListPresenter(private val apiInteractor: ApiInteractor) : MvpPresenter<IMoviesListView>() {

    private val paginator: Paginator = Paginator { offset -> loadMovies(offset) }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadMovies()
    }

    private fun loadMovies(page: Int = 1) {
        apiInteractor.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgress() }
                .subscribe({
                    paginator.totalPagesNumber = it.totalPages
                    paginator.received(it.page)
                    viewState.addMovies(it.results.map { MovieItem(it) })
                }, {
                    Timber.e(it)
                })
    }

    fun loadMore() {
        paginator.next()
    }
}