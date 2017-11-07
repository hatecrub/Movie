package com.hatecrab.movies.utils

import android.support.v7.app.AppCompatActivity
import com.hatecrab.movies.R
import com.hatecrab.movies.data.Movie
import com.hatecrab.movies.ui.movie.view.MovieFragment

class Router(private val activity: AppCompatActivity) {

    fun openMovieScreen(movie: Movie) {
        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MovieFragment.newInstance(movie))
                .addToBackStack(null)
                .commit()
    }
}