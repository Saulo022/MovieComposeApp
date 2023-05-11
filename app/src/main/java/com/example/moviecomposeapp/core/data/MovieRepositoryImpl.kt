package com.example.moviecomposeapp.core.data

import com.example.moviecomposeapp.core.data.local.MovieDao
import com.example.moviecomposeapp.core.data.local.entity.MovieEntity
import com.example.moviecomposeapp.core.data.local.entity.MovieType
import com.example.moviecomposeapp.core.data.mapper.toDomain
import com.example.moviecomposeapp.core.data.mapper.toEntity
import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.data.remote.extensions.resultOf
import com.example.moviecomposeapp.core.domain.model.FilterType
import com.example.moviecomposeapp.core.domain.model.Movie
import com.example.moviecomposeapp.core.domain.model.MovieList
import com.example.moviecomposeapp.core.domain.repository.MovieRepository
import com.example.moviecomposeapp.core.domain.usecase.ReduceFilteredMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val api: MovieApiTMDB,
    private val dao: MovieDao,
    private val reduceFilteredMovies: ReduceFilteredMovies
) :
    MovieRepository {

    override fun getAllMovies(filterType: FilterType, isFilteredOnly: Boolean): Flow<MovieList> {
        return flow {
            emit(getMovieListLocally(filterType))
            if (!isFilteredOnly) {
                getUpcomingMoviesRemotely().onSuccess {
                    saveMoviesLocally(it, MovieType.UPCOMING)
                    emit(getMovieListLocally(filterType))
                }.onFailure { println() }

                getPopularMoviesRemotely().onSuccess {
                    saveMoviesLocally(it, MovieType.TRENDING)
                    emit(getMovieListLocally(filterType))
                }.onFailure { println() }

                getMoviesByLanguagerRemotely("es").onSuccess {
                    saveMoviesLocally(it, MovieType.SPANISH)
                    emit(getMovieListLocally(filterType))
                }.onFailure { println() }

                getMoviesByYearRemotely(1993).onSuccess {
                    saveMoviesLocally(it, MovieType.NINETY_THREE)
                    emit(getMovieListLocally(filterType))
                }.onFailure { println() }
            }
        }
    }

    private suspend fun saveMoviesLocally(movies: List<Movie>, movieType: MovieType) {
        movies.forEach { dao.insertMovie(it.toEntity(movieType)) }
    }

    private suspend fun getMovieListLocally(filterType: FilterType): MovieList {
        val localMovies = dao.getMovies()

        val movieTypeFromFilter = when (filterType) {
            FilterType.SPANISH -> MovieType.SPANISH
            FilterType.NINETY_THREE -> MovieType.NINETY_THREE
        }

        return MovieList(
            upcoming = filterAndMapMovies(localMovies, MovieType.UPCOMING),
            trending = filterAndMapMovies(localMovies, MovieType.TRENDING),
            filtered =  reduceFilteredMovies(filterAndMapMovies(localMovies, movieTypeFromFilter))
        )
    }

    private suspend fun getUpcomingMoviesRemotely() = resultOf {
        val results = api.getUpcomingMovies().results
        val movies = results.map { it.toDomain() }
        movies
    }

    private suspend fun getPopularMoviesRemotely() = resultOf {
        val results = api.getPopularMovies().results
        val movies = results.map { it.toDomain() }
        movies
    }


    private suspend fun getMoviesByYearRemotely(year: Int) = resultOf {
        val results = api.getMoviesByYear(year).results
        val movies = results.map { it.toDomain() }
        movies
    }

    private suspend fun getMoviesByLanguagerRemotely(language: String) = resultOf {
        val results = api.getMoviesByLanguage(language).results
        val movies = results.map { it.toDomain() }
        movies
    }

    private fun filterAndMapMovies(movies: List<MovieEntity>, movieType: MovieType): List<Movie> {
        return movies.filter { it.type == movieType }.map { it.toDomain() }
    }
}