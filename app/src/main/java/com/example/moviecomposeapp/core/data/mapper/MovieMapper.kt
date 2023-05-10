package com.example.moviecomposeapp.core.data.mapper

import com.example.moviecomposeapp.core.data.local.entity.MovieEntity
import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.data.remote.response.MovieResult
import com.example.moviecomposeapp.core.domain.model.Movie
import com.example.moviecomposeapp.core.domain.model.MovieType

fun MovieResult.toDomain(): Movie {
    return Movie(
        id = this.id,
        poster = MovieApiTMDB.IMAGE_URL + this.posterPath
    )
}

fun Movie.toEntity(type: MovieType): MovieEntity {
    return MovieEntity(
        id = this.id,
        poster = this.poster,
        type = type
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        poster = this.poster
    )
}