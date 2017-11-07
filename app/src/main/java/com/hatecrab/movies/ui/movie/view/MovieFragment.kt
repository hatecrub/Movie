package com.hatecrab.movies.ui.movie.view

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.hatecrab.movies.R
import com.hatecrab.movies.data.Country
import com.hatecrab.movies.data.Genre
import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.data.MovieFullInfoResponse
import com.hatecrab.movies.di.movie.MovieModule
import com.hatecrab.movies.ui.common.BaseFragment
import com.hatecrab.movies.ui.movie.presenter.MoviePresenter
import com.hatecrab.movies.utils.*
import kotlinx.android.synthetic.main.movie_fragment.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

class MovieFragment : BaseFragment(), IMovieView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MoviePresenter

    @ProvidePresenter
    fun providePresenter(): MoviePresenter {
        return presenter
    }

    private val movie by lazy { arguments.getSerializable(MOVIE_ARG) as Movie }

    override fun onCreate(savedInstanceState: Bundle?) {
        getActivityComponent().plus(MovieModule()).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setMovie(movie)
        presenter.loadMovieFullInfo()

        movieBanner.loadImage(movie.backdropPath)
        movieLogo.loadImage(movie.posterPath)
        collapsingToolbarLayout.isTitleEnabled = false
        toolbar.title = movie.title
        movieDescription.text = movie.overview
        movieRating.text = getString(R.string.movie_rating, movie.voteAverage)
    }

    override fun onMovieFullInfoLoaded(movieFullInfoResponse: MovieFullInfoResponse, genres: List<Genre>, similarMovies: List<Movie>) {
        movieBudget.makeVisible()
        movieBudget.text =buildBudget(movieFullInfoResponse.budget)

        if (movieFullInfoResponse.productionCountries.isNotEmpty()) {
            movieInfo.makeVisible()
            movieInfo.text = buildInfo(movieFullInfoResponse.productionCountries)
        }

        if (genres.isNotEmpty()) {
            movieGenres.makeVisible()
            movieGenres.text = buildGenres(genres)
        }
    }

    private fun buildBudget(budget: Int): String {
        val symbols  = DecimalFormatSymbols()
        symbols.decimalSeparator = ' '
        val decimalFormat = DecimalFormat("$###,###,###,###", symbols)
        return getString(R.string.movie_budget, decimalFormat.format(budget))
    }

    private fun buildInfo(countries: List<Country>): SpannableStringBuilder {
        val builder = SpannableStringBuilder()

        countries.forEach {
            builder.append(it.name)
            if (countries.indexOf(it) != countries.size - 1) {
                builder.append(INFO_SEPARATOR)
            }
        }

        if (movie.adult) {
            val ageLevel = getString(R.string.adult_film_age_level)
            builder.append(INFO_SEPARATOR)
            builder.append(ageLevel)
            builder.setSpan(RoundedBackgroundSpan(context.getColorCompat(R.color.white_30)),
                    builder.length - ageLevel.length,
                    builder.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return builder
    }

    private fun buildGenres(genres: List<Genre>) : SpannableStringBuilder {
        val builder = SpannableStringBuilder()
        genres.forEach {
            builder.append(it.name)
            builder.setSpan(RoundedBackgroundSpan(context.getColorCompat(R.color.black_50), true),
                    builder.length - it.name.length,
                    builder.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            builder.append(GENRES_SEPARATOR)
        }
        return builder
    }

    companion object {
        private const val INFO_SEPARATOR = " / "
        private const val GENRES_SEPARATOR = "  "
        private const val MOVIE_ARG = "MOVIE_ARG"

        fun newInstance(movie: Movie) = MovieFragment().withArguments(MOVIE_ARG to movie)
    }
}