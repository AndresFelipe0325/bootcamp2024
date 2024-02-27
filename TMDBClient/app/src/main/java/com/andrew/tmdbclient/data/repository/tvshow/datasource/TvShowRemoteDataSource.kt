package com.andrew.tmdbclient.data.repository.tvshow.datasource

import com.andrew.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShowsFromAPI() : Response<TvShowList>
}