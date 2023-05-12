package com.example.moviecomposeapp.detail.presentation

import com.example.moviecomposeapp.core.domain.model.MovieDetail

data class DetailMovieState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false
)
