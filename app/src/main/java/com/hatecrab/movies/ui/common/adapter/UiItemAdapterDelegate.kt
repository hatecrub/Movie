package com.hatecrab.movies.ui.common.adapter

import android.support.v7.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import com.hatecrab.movies.ui.common.items.UiItem

abstract class UiItemAdapterDelegate<I : UiItem, VH : RecyclerView.ViewHolder>
    : AbsListItemAdapterDelegate<I, UiItem, VH>()