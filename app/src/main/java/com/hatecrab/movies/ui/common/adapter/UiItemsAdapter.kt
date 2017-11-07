package com.hatecrab.movies.ui.common.adapter

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.hatecrab.movies.ui.common.items.LoadMoreErrorItem
import com.hatecrab.movies.ui.common.items.LoadMoreProgressItem
import com.hatecrab.movies.ui.common.items.UiItem

abstract class UiItemsAdapter : ListDelegationAdapter<MutableList<UiItem>>() {

    override fun setItems(items: MutableList<UiItem>) {
        super.setItems(items)
        notifyDataSetChanged()
    }

    fun add(list: List<UiItem>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun progress() {
        removeErrorAndProgress()
        items.add(LoadMoreProgressItem(itemCount))
        notifyDataSetChanged()
    }

    fun error(errorMainMessage: CharSequence? = null, errorAdditionalMessage: CharSequence? = null) {
        removeErrorAndProgress()
        items.add(LoadMoreErrorItem(itemCount, errorMainMessage, errorAdditionalMessage))
        notifyDataSetChanged()
    }

    fun removeErrorAndProgress() {
        items.removeAll { it is LoadMoreErrorItem || it is LoadMoreProgressItem }
    }
}