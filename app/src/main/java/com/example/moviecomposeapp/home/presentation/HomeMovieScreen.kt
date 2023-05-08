package com.example.moviecomposeapp.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.home.presentation.components.CategoryTitle
import com.example.moviecomposeapp.home.presentation.components.HomeMovieHeader
import com.example.moviecomposeapp.home.presentation.components.HomeMovieList
import com.example.moviecomposeapp.home.presentation.components.HomeRecommended

@Composable
fun HomeMovieScreen(viewModel: HomeMovieViewModel = hiltViewModel()) {
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp)
    ) {
        item {
            HomeMovieHeader()
        }
        if (state.upcomingMovies.isNotEmpty()) {
            item {
                HomeMovieList(
                    title = "Proximos Estrenos",
                    posters = state.upcomingMovies.map { it.poster })
            }
        }
        item { Spacer(modifier = Modifier.height(26.dp)) }
        if (state.popularMovies.isNotEmpty()) {
            item {
                HomeMovieList(
                    title = "Tendencia",
                    posters = state.popularMovies.map { it.poster })
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            HomeRecommended(
                selectedFilter = state.selectedFilter,
                onFilterClick = { viewModel.onEvent(HomeEvent.ChangeFilter(it))},
                movieList = state.filteredMovies
            ) {

            }
        }
    }
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}