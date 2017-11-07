package com.hatecrab.movies.utils

import android.content.Context
import android.graphics.Point
import com.hatecrab.movies.R

class UiCalculator(private val context: Context) {

    val displayWidth by lazy { context.getDisplaySize().x }
    val displayHeight by lazy { context.getDisplaySize().y }

    private val defaultMediaItemCardHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.movie_card_height) }
    private val defaultMediaItemCardWidth by lazy { context.resources.getDimensionPixelSize(R.dimen.movie_card_width) }
    private val miniMediaItemCardHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.movie_card_height_mini) }
    private val miniMediaItemCardWidth by lazy { context.resources.getDimensionPixelSize(R.dimen.movie_card_width_mini) }

    private val horizontalSpacing by lazy { context.resources.getDimensionPixelSize(R.dimen.horizontal_space_between_cards) }
    private val contentWidth = displayWidth - horizontalSpacing * 2

    fun calculateMediaItemCardSize(isMini: Boolean): Point {
        if (isMini) {
            return calculateMediaItemCardSizeMini()
        }
        val dividersCount = context.resources.getInteger(R.integer.grid_span_size) - 1
        val width = (contentWidth - horizontalSpacing * dividersCount) / context.resources.getInteger(R.integer.grid_span_size)
        val height = (width * (defaultMediaItemCardHeight.toDouble() / defaultMediaItemCardWidth)).toInt()
        return Point(width, height)
    }

    private fun calculateMediaItemCardSizeMini(): Point {
        val cardCounts = context.resources.getInteger(R.integer.grid_span_size_mini)
        val width = (contentWidth - context.resources.getDimensionPixelSize(R.dimen.vertical_space_between_cards) * cardCounts) / cardCounts
        val height = (width * (miniMediaItemCardHeight.toDouble() / miniMediaItemCardWidth)).toInt()
        return Point(width, height)
    }
}