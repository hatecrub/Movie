package com.hatecrab.movies.ui.common

import com.arellomobile.mvp.MvpAppCompatFragment
import com.hatecrab.movies.MainActivity

open class BaseFragment : MvpAppCompatFragment() {

    fun getActivityComponent() = (activity as MainActivity).activityComponent
}