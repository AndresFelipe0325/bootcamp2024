package com.andrew.tmdbclient.domain.repository

import com.andrew.tmdbclient.data.model.tvshow.TvShow

interface TvShowRepository {

    suspend fun getTvShows() : List<TvShow>?

    suspend fun updateTvShows() : List<TvShow>?
}