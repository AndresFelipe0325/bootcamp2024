package com.andrew.tmdbclient.data.repository.movie.datasource

import com.andrew.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies() : Response<MovieList>
}