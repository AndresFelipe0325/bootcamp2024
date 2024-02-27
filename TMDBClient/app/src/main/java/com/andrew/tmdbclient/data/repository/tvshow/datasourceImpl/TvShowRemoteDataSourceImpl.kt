package com.andrew.tmdbclient.data.repository.tvshow.datasourceImpl

import com.andrew.tmdbclient.data.api.TMDBService
import com.andrew.tmdbclient.data.model.tvshow.TvShowList
import com.andrew.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(private val tmdbService: TMDBService,
    private val apiKey: String) : TvShowRemoteDataSource{
    override suspend fun getTvShowsFromAPI(): Response<TvShowList> =
        tmdbService.getPopularTvShows(apiKey)
}