package com.example.moviecomposeapp.core.data.mapper

import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.data.remote.response.MovieResult
import com.example.moviecomposeapp.core.domain.model.Movie

fun MovieResult.toDomain():Movie{
    return Movie(
        description = this.overview,
        title = this.title,
        releaseYear = this.releaseDate.substring(0,4).toInt(),
        language = this.originalLanguage,
        rating = this.voteAverage,
        poster = MovieApiTMDB.BASE_URL + this.posterPath,
        genres = this.genreIds
    )
}