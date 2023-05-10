package com.example.moviecomposeapp.core.domain.model

data class MovieList(
    val movieMap: MutableMap<MovieType, List<Movie>>
)
