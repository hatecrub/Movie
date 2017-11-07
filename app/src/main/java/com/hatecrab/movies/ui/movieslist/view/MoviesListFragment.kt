package com.hatecrab.movies.ui.movieslist.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.hatecrab.movies.R
import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.di.movie.MovieModule
import com.hatecrab.movies.ui.common.BaseFragment
import com.hatecrab.movies.ui.common.items.MovieItem
import com.hatecrab.movies.ui.movieslist.presenter.MoviesListPresenter
import com.hatecrab.movies.ui.movieslist.view.adapter.MoviesListAdapter
import com.hatecrab.movies.utils.GridSpaceItemDecoration
import com.hatecrab.movies.utils.OnPageScrollListener
import com.hatecrab.movies.utils.Router
import com.hatecrab.movies.utils.UiCalculator
import kotlinx.android.synthetic.main.movies_list_fragment.*
import javax.inject.Inject

class MoviesListFragment : BaseFragment(), IMoviesListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MoviesListPresenter

    @ProvidePresenter
    fun providePresenter(): MoviesListPresenter {
        return presenter
    }

    @Inject
    lateinit var uiCalculator: UiCalculator

    @Inject
    lateinit var router: Router

    private lateinit var moviesAdapter: MoviesListAdapter
    private val scrollListener by lazy { OnPageScrollListener({ presenter.loadMore() }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        getActivityComponent().plus(MovieModule()).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.app_name)

        val verticalSpacing = resources.getDimensionPixelSize(R.dimen.horizontal_space_between_cards)
        val horizontalSpacing = resources.getDimensionPixelSize(R.dimen.horizontal_space_between_cards)

        val spanCount = context.resources.getInteger(R.integer.grid_span_size)
        moviesAdapter = MoviesListAdapter(uiCalculator, { movie, imageView ->  openMovieScreen(movie, imageView) }, { _, _ -> presenter.loadMore() })
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, spanCount).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int) = when (adapter.getItemViewType(position)) {
                        0 -> 1
                        else -> spanCount
                    }
                }
            }
            adapter = moviesAdapter
            addOnScrollListener(scrollListener)
            addItemDecoration(GridSpaceItemDecoration(spanCount, horizontalSpacing, verticalSpacing, false, GridLayoutManager.VERTICAL))
        }
    }

    override fun addMovies(movies: List<MovieItem>) {
        moviesAdapter.removeErrorAndProgress()
        moviesAdapter.add(movies)
    }

    override fun showProgress() {
        moviesAdapter.progress()
    }

    override fun showError(errorMessage: CharSequence?) {
        moviesAdapter.error(errorAdditionalMessage = errorMessage)
    }

    private fun openMovieScreen(movie: Movie, imageForTransition: ImageView? = null) {
        router.openMovieScreen(movie, imageForTransition)
    }
}