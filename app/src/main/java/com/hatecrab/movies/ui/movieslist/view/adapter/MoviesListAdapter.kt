package com.hatecrab.movies.ui.movieslist.view.adapter

import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.ui.common.adapter.LoadMoreErrorViewAdapterDelegate
import com.hatecrab.movies.ui.common.adapter.LoadMoreProgressAdapterDelegate
import com.hatecrab.movies.ui.common.adapter.UiItemsAdapter
import com.hatecrab.movies.ui.common.items.*
import com.hatecrab.movies.utils.UiCalculator
import java.util.ArrayList

class MoviesListAdapter(uiCalculator: UiCalculator, listener: (Movie) -> Unit, loadMoreClickListener: (Int, LoadMoreErrorItem) -> Unit) : UiItemsAdapter() {
    init {
        items = ArrayList()
        delegatesManager.addDelegate(MovieAdapterDelegate(uiCalculator, listener))
        delegatesManager.addDelegate(LoadMoreProgressAdapterDelegate())
        delegatesManager.addDelegate(LoadMoreErrorViewAdapterDelegate(loadMoreClickListener))
    }
}