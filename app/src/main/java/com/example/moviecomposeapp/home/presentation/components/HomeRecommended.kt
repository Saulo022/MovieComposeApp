package com.example.moviecomposeapp.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviecomposeapp.core.domain.model.Movie
import com.example.moviecomposeapp.home.presentation.FilterType

@Composable
fun HomeRecommended(
    selectedFilter: FilterType,
    onFilterClick: (FilterType) -> Unit,
    movieList: List<Movie>,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit
) {
    if (movieList.isEmpty()) {
        Column(modifier = modifier.fillMaxWidth()) {
            CategoryTitle(title = "Recomendados para ti")
            Spacer(modifier = Modifier.height(12.dp))
            FilterPillContainer(
                filters = FilterType.values().toList(),
                selectedFilter = selectedFilter,
                onFilterClick = onFilterClick
            )
        }
    }
}