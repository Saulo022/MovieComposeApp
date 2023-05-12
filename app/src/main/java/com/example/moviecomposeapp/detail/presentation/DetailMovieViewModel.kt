package com.example.moviecomposeapp.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.core.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(DetailMovieState())
        private set

    init {
        state = state.copy(
            isLoading = true
        )
        val movieId = savedStateHandle.get<Int>("movie_id")
        movieId?.let {
            viewModelScope.launch {
                repository.getMovieById(movieId).onSuccess {
                    state = state.copy(
                        movie = it
                    )
                }.onFailure {
                    println()
                }
                state = state.copy(
                    isLoading = false
                )
            }
        } ?: kotlin.run {
            println("El movie id es null")
        }
    }
}