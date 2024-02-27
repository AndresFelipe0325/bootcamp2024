package com.andrew.tmdbclient.data.repository.tvshow.datasource

import com.andrew.tmdbclient.data.model.tvshow.TvShow

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache() : List<TvShow>
    suspend fun saveTvShowsToCache(tvShows: List<TvShow>)
}