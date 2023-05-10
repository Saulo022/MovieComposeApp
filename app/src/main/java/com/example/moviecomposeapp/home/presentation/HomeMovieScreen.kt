package com.example.moviecomposeapp.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.moviecomposeapp.home.presentation.components.*

const val COLUMNS_IN_GRID = 2

@Composable
fun HomeMovieScreen(viewModel: HomeMovieViewModel = hiltViewModel()) {
    val state = viewModel.state
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(COLUMNS_IN_GRID) }) {
            HomeMovieHeader()
        }

        if (state.upcomingMovies.isNotEmpty()) {
            item(span = { GridItemSpan(COLUMNS_IN_GRID) }) {
                HomeMovieList(
                    title = "Proximos Estrenos",
                    posters = state.upcomingMovies.map { it.poster })
            }
        }

        if (state.popularMovies.isNotEmpty()) {
            item(span = { GridItemSpan(COLUMNS_IN_GRID) }) {
                HomeMovieList(
                    title = "Tendencia",
                    posters = state.popularMovies.map { it.poster })
            }
        }

        if (state.filteredMovies.isNotEmpty()){
            item(span = { GridItemSpan(COLUMNS_IN_GRID) }) {
                HomeRecommended(
                    selectedFilter = state.selectedFilter,
                    onFilterClick = { viewModel.onEvent(HomeEvent.ChangeFilter(it)) })
            }
        }

        items(state.filteredMovies) {
            HomeMoviePoster(it.poster, MoviePosterSize.BIG)
        }
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}