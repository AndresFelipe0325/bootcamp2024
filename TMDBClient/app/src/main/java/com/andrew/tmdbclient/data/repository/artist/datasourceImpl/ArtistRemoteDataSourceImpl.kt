package com.andrew.tmdbclient.data.repository.artist.datasourceImpl

import com.andrew.tmdbclient.data.api.TMDBService
import com.andrew.tmdbclient.data.model.artist.ArtistList
import com.andrew.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(private val tmdbService: TMDBService,
    private val apiKey: String) : ArtistRemoteDataSource {
    override suspend fun getArtistsFromAPI(): Response<ArtistList> =
        tmdbService.getPopularArtists(apiKey)
}