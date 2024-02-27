package com.andrew.tmdbclient.domain.usecase

import com.andrew.tmdbclient.data.model.movie.Movie
import com.andrew.tmdbclient.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute() : List<Movie>? = movieRepository.getMovies()
}