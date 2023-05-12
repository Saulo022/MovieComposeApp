package com.example.moviecomposeapp.home.presentation

import com.example.moviecomposeapp.core.domain.model.FilterType

sealed class HomeEvent {
    data class ChangeFilter(val filterType: FilterType) : HomeEvent()
}
