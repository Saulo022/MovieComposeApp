package com.example.moviecomposeapp.home.presentation

sealed class HomeEvent {
    data class ChangeFilter(val filterType: FilterType) : HomeEvent()
    data class OnMovieClick(val movieId: Int) : HomeEvent()
}
