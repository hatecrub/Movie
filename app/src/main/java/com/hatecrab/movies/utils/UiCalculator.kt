package com.hatecrab.movies.utils

import android.content.Context
import android.graphics.Point
import com.hatecrab.movies.R

class UiCalculator(private val context: Context) {

    val displayWidth by lazy { context.getDisplaySize().x }
    val displayHeight by lazy { context.getDisplaySize().y }

    private val defaultMediaItemCardHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.movie_card_height) }
    private val defaultMediaItemCardWidth by lazy { context.resources.getDimensionPixelSize(R.dimen.movie_card_width) }

    private val horizontalSpacing by lazy { context.resources.getDimensionPixelSize(R.dimen.horizontal_space_between_cards) }
    private val contentWidth = displayWidth - horizontalSpacing * 2

    fun calculateMediaItemCardSize(): Point {
        val width = (contentWidth - horizontalSpacing) / context.resources.getInteger(R.integer.grid_span_size)
        val height = (width * (defaultMediaItemCardHeight.toDouble() / defaultMediaItemCardWidth)).toInt()
        return Point(width, height)
    }
}