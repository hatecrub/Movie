package com.hatecrab.movies.ui.common.adapter

import android.view.ViewGroup
import com.hatecrab.movies.ui.common.items.LoadMoreErrorItem
import com.hatecrab.movies.ui.common.items.UiItem

class LoadMoreErrorViewAdapterDelegate(private val clickListener: (Int, LoadMoreErrorItem)-> Unit)
    : UiItemAdapterDelegate<LoadMoreErrorItem, LoadMoreErrorViewHolder>() {

    override fun isForViewType(item: UiItem, items: MutableList<UiItem>, position: Int): Boolean =
            item is LoadMoreErrorItem

    override fun onBindViewHolder(item: LoadMoreErrorItem, viewHolder: LoadMoreErrorViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup) = LoadMoreErrorViewHolder.createViewHolder(parent)

}