package com.example.moviecomposeapp.core.data.remote

import com.example.moviecomposeapp.core.data.remote.response.MovieResponse
import retrofit2.http.GET

interface MovieApiTMDB {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponse
}