package com.example.moviecomposeapp.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.core.domain.model.FilterType
import com.example.moviecomposeapp.core.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeMovieViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    var state by mutableStateOf(HomeMovieState())
        private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            supervisorScope {
                val movies = launch { getAllMovies() }
                movies.join()
                state = state.copy(isLoading = false)
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeFilter -> {
                if (event.filterType != state.selectedFilter) {
                    state = state.copy(selectedFilter = event.filterType)
                    viewModelScope.launch {
                        repository.getAllMovies(state.selectedFilter, true).collect {
                            state = state.copy(
                                filteredMovies = it.filtered
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun getAllMovies() {
        repository.getAllMovies(state.selectedFilter, false).collect {
            state = state.copy(
                upcomingMovies = it.upcoming,
                popularMovies = it.trending,
                filteredMovies = it.filtered
            )
        }
    }

}