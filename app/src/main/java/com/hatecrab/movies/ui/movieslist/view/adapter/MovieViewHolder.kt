package com.hatecrab.movies.ui.movieslist.view.adapter

import android.view.View
import android.view.ViewGroup
import com.hatecrab.movies.R
import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.ui.common.adapter.DumbViewHolder
import com.hatecrab.movies.utils.UiCalculator
import com.hatecrab.movies.utils.inflate
import com.hatecrab.movies.utils.loadImage
import com.hatecrab.movies.utils.setSize
import kotlinx.android.synthetic.main.movie_card.*

class MovieViewHolder(view: View) : DumbViewHolder(view) {
    fun bind(movie: Movie, clickListener: (Movie) -> Unit): MovieViewHolder {
        title.text = movie.originalTitle
        rating.text = movie.voteAverage.toString()
        logo.loadImage(movie.posterPath)
        itemView.setOnClickListener { clickListener(movie) }
        return this
    }

    companion object {
        fun createViewHolder(parent: ViewGroup, uiCalculator: UiCalculator, isMini: Boolean = false): MovieViewHolder {
            return MovieViewHolder(parent.inflate(R.layout.movie_card).apply { setSize(uiCalculator.calculateMediaItemCardSize(isMini)) })
        }

        fun createMediaItemView(parent: ViewGroup,
                                uiCalculator: UiCalculator,
                                movie: Movie,
                                isMini: Boolean,
                                clickListener: (Movie) -> Unit): View =
                createViewHolder(parent, uiCalculator, isMini).bind(movie, clickListener).containerView
    }
}