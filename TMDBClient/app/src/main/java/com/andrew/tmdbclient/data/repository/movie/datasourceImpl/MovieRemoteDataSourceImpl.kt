package com.andrew.tmdbclient.data.repository.movie.datasourceImpl

import com.andrew.tmdbclient.data.api.TMDBService
import com.andrew.tmdbclient.data.model.movie.MovieList
import com.andrew.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val tmdbService: TMDBService,
    private val apiKey: String) : MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)

}