package com.example.moviecomposeapp.core.data

import com.example.moviecomposeapp.core.data.mapper.toDomain
import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.domain.model.Movie
import com.example.moviecomposeapp.core.domain.repository.MovieRepository

class MovieRepositoryImpl(val api: MovieApiTMDB): MovieRepository {
    override suspend fun getUpcomingMovies(): Result<List<Movie>> {
        return try {
            val results = api.getUpcomingMovies().results
            Result.success(results.map { it.toDomain() })
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}