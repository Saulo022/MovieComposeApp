package com.example.moviecomposeapp.core.domain.repository

import com.example.moviecomposeapp.core.domain.model.FilterType
import com.example.moviecomposeapp.core.domain.model.Movie
import com.example.moviecomposeapp.core.domain.model.MovieDetail
import com.example.moviecomposeapp.core.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
     fun getAllMovies(filterType: FilterType, isFilteredOnly: Boolean): Flow<MovieList>
     suspend fun getMovieById(id: Int): Result<MovieDetail>
}