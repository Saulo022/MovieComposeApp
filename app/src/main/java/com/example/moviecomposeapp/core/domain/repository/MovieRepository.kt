package com.example.moviecomposeapp.core.domain.repository

import com.example.moviecomposeapp.core.domain.model.Movie

interface MovieRepository {
    suspend fun getUpcomingMovies(): Result<List<Movie>>
    suspend fun getPopularMovies(): Result<List<Movie>>
}