package com.hatecrab.movies.ui.common

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import rx.Subscription
import rx.subscriptions.CompositeSubscription

abstract class BasePresenter<View: MvpView>: MvpPresenter<View>() {

    private val subscriptions = CompositeSubscription()

    fun Subscription.unsubscribeOnDestroy(): Subscription {
        subscriptions.add(this)
        return this
    }

    override fun onDestroy() {
        subscriptions.unsubscribe()
        super.onDestroy()
    }
}