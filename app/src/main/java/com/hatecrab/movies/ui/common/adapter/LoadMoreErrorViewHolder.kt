package com.hatecrab.movies.ui.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hatecrab.movies.R
import com.hatecrab.movies.ui.common.items.LoadMoreErrorItem
import com.hatecrab.movies.utils.inflate
import com.hatecrab.movies.utils.makeGone
import com.hatecrab.movies.utils.makeVisible
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.error_view.*

class LoadMoreErrorViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(clickListener: (Int, LoadMoreErrorItem) -> Unit, item: LoadMoreErrorItem): LoadMoreErrorViewHolder {
        if (item.position == 0) {
            errorLogo.makeVisible()
        } else {
            errorLogo.makeGone()
        }
        item.errorMainMessage?.let { errorMainMessage.text = it }
        item.errorAdditionalMessage?.let { errorAdditionalMessage.text = it }
        errorRetryButton.setOnClickListener { clickListener(R.id.errorRetryButton, item) }
        return this
    }

    companion object {
        fun createViewHolder(parent: ViewGroup) = LoadMoreErrorViewHolder(parent.inflate(R.layout.error_view))
    }
}