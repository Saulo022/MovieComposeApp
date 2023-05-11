package com.example.moviecomposeapp.core.domain.model

data class MovieList(
    val upcoming: List<Movie>,
    val trending: List<Movie>,
    val filtered: List<Movie>
)
