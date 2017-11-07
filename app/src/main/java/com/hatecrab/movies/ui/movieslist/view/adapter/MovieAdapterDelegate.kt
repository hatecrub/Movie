package com.hatecrab.movies.ui.movieslist.view.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.ui.common.adapter.UiItemAdapterDelegate
import com.hatecrab.movies.ui.common.items.MovieItem
import com.hatecrab.movies.ui.common.items.UiItem
import com.hatecrab.movies.utils.UiCalculator

class MovieAdapterDelegate(private val uiCalculator: UiCalculator, private val clickListener: (Movie, ImageView?) -> Unit)
    : UiItemAdapterDelegate<MovieItem, MovieViewHolder>() {

    override fun isForViewType(item: UiItem, items: MutableList<UiItem>, position: Int) = item is MovieItem

    override fun onCreateViewHolder(parent: ViewGroup): MovieViewHolder {
        return MovieViewHolder.createViewHolder(parent, uiCalculator)
    }

    override fun onBindViewHolder(item: MovieItem, viewHolder: MovieViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item.movie, clickListener)
    }
}