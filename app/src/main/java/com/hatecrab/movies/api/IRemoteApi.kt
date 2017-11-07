package com.hatecrab.movies.api

import com.hatecrab.movies.data.GenreListResponse
import com.hatecrab.movies.data.MovieFullInfoResponse
import com.hatecrab.movies.data.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface IRemoteApi {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Observable<MovieListResponse>

    @GET("genre/movie/list")
    fun getGenresList(): Observable<GenreListResponse>

    @GET("movie/{movie_id}")
    fun getMovieFullInfo(@Path("movie_id") id: Int): Observable<MovieFullInfoResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") id: Int): Observable<MovieListResponse>
}