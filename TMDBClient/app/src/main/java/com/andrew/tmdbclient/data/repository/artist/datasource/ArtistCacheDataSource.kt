package com.andrew.tmdbclient.data.repository.artist.datasource

import com.andrew.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache() : List<Artist>
    suspend fun saveArtistsToCache(artists: List<Artist>)

}