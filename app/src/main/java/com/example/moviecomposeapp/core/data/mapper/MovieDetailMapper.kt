package com.example.moviecomposeapp.core.data.mapper

import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.data.remote.response.MovieDetailResponse
import com.example.moviecomposeapp.core.domain.model.MovieDetail

fun MovieDetailResponse.toDomain(): MovieDetail {
    return MovieDetail(
        id = this.id,
        image = MovieApiTMDB.IMAGE_URL + this.posterPath,
        name = this.originalTitle,
        year = this.releaseDate.substring(0, 4).toInt(),
        language = this.originalLanguage,
        rating = String.format("%.1f", this.voteAverage).toDouble(),
        genres = this.genres.map { it.name },
        description = this.overview
    )
}