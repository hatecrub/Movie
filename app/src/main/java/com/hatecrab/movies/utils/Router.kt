package com.hatecrab.movies.utils

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.widget.ImageView
import com.hatecrab.movies.R
import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.ui.movie.view.MovieFragment

class Router(private val activity: AppCompatActivity) {

    fun openMovieScreen(movie: Movie, imageForTransition: ImageView? = null) {
        val nextFragment = MovieFragment.newInstance(movie)
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && imageForTransition != null) {
            val enterTransitionSet = TransitionSet()
            enterTransitionSet.addTransition(TransitionInflater.from(activity.applicationContext).inflateTransition(android.R.transition.move))
            enterTransitionSet.duration = 200
            nextFragment.sharedElementEnterTransition = enterTransitionSet

            imageForTransition.transitionName = TRANSITION_NAME
            fragmentTransaction.addSharedElement(imageForTransition, imageForTransition.transitionName)
        }

        fragmentTransaction.replace(R.id.fragmentContainer, nextFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    companion object {
        const val TRANSITION_NAME = "transition"
    }
}