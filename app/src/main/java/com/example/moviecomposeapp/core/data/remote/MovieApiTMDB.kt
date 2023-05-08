package com.example.moviecomposeapp.core.data.remote

import com.example.moviecomposeapp.core.data.remote.response.MovieResponse
import retrofit2.http.GET

interface MovieApiTMDB {
    companion object{
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse
}