package com.hatecrab.movies.data

data class MovieListResponse(val page: Int, val totalResults: Int, val totalPages: Int, val results: List<Movie>)

data class GenreListResponse(val genres: List<Genre>)

data class MovieFullInfoResponse(val budget: Int, val productionCountries: List<Country>)