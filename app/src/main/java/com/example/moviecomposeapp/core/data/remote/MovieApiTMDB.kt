package com.example.moviecomposeapp.core.data.remote

import com.example.moviecomposeapp.core.data.remote.response.MovieDetailResponse
import com.example.moviecomposeapp.core.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiTMDB {
    companion object{
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse
    @GET("discover/movie?sort_by=popularity.desc&include_adult=false")
    suspend fun getMoviesByYear(@Query("year") year: Int): MovieResponse

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false")
    suspend fun getMoviesByLanguage(@Query("with_original_language") language: String): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): MovieDetailResponse
}