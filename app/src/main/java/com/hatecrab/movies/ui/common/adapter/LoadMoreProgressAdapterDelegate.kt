package com.hatecrab.movies.ui.common.adapter

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.hatecrab.movies.R
import com.hatecrab.movies.ui.common.items.LoadMoreProgressItem
import com.hatecrab.movies.ui.common.items.UiItem
import com.hatecrab.movies.utils.inflate
import com.hatecrab.movies.utils.setHeight

class LoadMoreProgressAdapterDelegate
    : UiItemAdapterDelegate<LoadMoreProgressItem, DumbViewHolder>() {

    override fun isForViewType(item: UiItem, items: MutableList<UiItem>, position: Int): Boolean =
            item is LoadMoreProgressItem

    override fun onBindViewHolder(item: LoadMoreProgressItem, viewHolder: DumbViewHolder, payloads: MutableList<Any>) {
        viewHolder.itemView.setHeight(if (item.position == 0) MATCH_PARENT else WRAP_CONTENT)
    }

    override fun onCreateViewHolder(parent: ViewGroup) = DumbViewHolder(parent.inflate(R.layout.progress_item))
}