package com.example.moviecomposeapp.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.core.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMovieViewModel @Inject constructor(val repository: MovieRepository) : ViewModel() {

    var state by mutableStateOf(HomeMovieState())
        private set

    init {
        state = state.copy(isLoading = true)
        getUpcomingMovies()
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            repository.getUpcomingMovies().onSuccess {
                state = state.copy(
                    upcoming = it
                )
            }.onFailure {
                println()
            }
            state = state.copy(isLoading = false)
        }
    }
}