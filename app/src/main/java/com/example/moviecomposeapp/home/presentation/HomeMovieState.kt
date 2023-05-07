package com.example.moviecomposeapp.home.presentation

import com.example.moviecomposeapp.core.domain.model.Movie

data class HomeMovieState(
    val upcoming: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)
