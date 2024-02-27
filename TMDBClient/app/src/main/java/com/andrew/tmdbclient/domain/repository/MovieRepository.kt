package com.andrew.tmdbclient.domain.repository

import com.andrew.tmdbclient.data.model.movie.Movie

interface MovieRepository {

    suspend fun getMovies() : List<Movie>?

    suspend fun updateMovies() : List<Movie>?
}