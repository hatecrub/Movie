package com.hatecrab.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hatecrab.movies.di.activity.ActivityComponent
import com.hatecrab.movies.di.activity.ActivityModule
import com.hatecrab.movies.ui.movieslist.view.MoviesListFragment

class MainActivity : AppCompatActivity() {
    val activityComponent: ActivityComponent by lazy { (application as MobileApplication).appComponent.plus(ActivityModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        injectDependencies()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, MoviesListFragment())
                    .commit()
        }
    }

    fun injectDependencies() {
        activityComponent.inject(this)
    }
}