package com.example.moviecomposeapp.core.domain.repository

import com.example.moviecomposeapp.core.domain.model.Movie
import com.example.moviecomposeapp.core.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

     fun getUpcomingMovies(): Flow<List<Movie>>
     fun getPopularMovies(): Flow<List<Movie>>
     fun getMoviesByYear(year: Int): Flow<List<Movie>>
     fun getMoviesByLanguage(language: String): Flow<List<Movie>>
}