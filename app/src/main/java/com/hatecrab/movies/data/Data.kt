package com.hatecrab.movies.data

import java.io.Serializable

data class Movie(val voteCount: Int,
                 val id: Int,
                 val video: Boolean,
                 val voteAverage: Double,
                 val title: String,
                 val popularity: Double,
                 val posterPath: String,
                 val originalLanguage: String,
                 val originalTitle: String,
                 val genreIds: List<Int>,
                 val backdropPath: String,
                 val adult: Boolean,
                 val overview: String,
                 val releaseDate: String) : Serializable

data class Genre(val id: Int, val name: String) : Serializable

data class Country(val iso_3166_1: String, val name: String) : Serializable