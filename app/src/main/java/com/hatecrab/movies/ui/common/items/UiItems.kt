package com.hatecrab.movies.ui.common.items

import com.hatecrab.movies.data.Movie

interface UiItem
class LoadMoreErrorItem(val position: Int = -1, val errorMainMessage: CharSequence? = null, val errorAdditionalMessage: CharSequence? = null) : UiItem
class LoadMoreProgressItem(val position: Int = -1) : UiItem
class MovieItem(val movie: Movie): UiItem