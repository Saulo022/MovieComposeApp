package com.example.moviecomposeapp.core.data

import com.example.moviecomposeapp.core.data.mapper.toDomain
import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.data.remote.extensions.resultOf
import com.example.moviecomposeapp.core.domain.model.Movie
import com.example.moviecomposeapp.core.domain.repository.MovieRepository

class MovieRepositoryImpl(private val api: MovieApiTMDB): MovieRepository {
    override suspend fun getUpcomingMovies() = resultOf {
        val results = api.getUpcomingMovies().results
        results.map { it.toDomain() }
    }


    override suspend fun getPopularMovies()= resultOf {
        val results = api.getPopularMovies().results
        results.map { it.toDomain() }
    }
}