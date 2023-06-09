package com.example.moviecomposeapp.home.presentation

import com.example.moviecomposeapp.core.domain.model.FilterType
import com.example.moviecomposeapp.core.domain.model.Movie

data class HomeMovieState(
    val upcomingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val selectedFilter: FilterType = FilterType.SPANISH,
    val filteredMovies: List<Movie> = emptyList()
)
