package com.andrew.tmdbclient.data.repository.artist.datasource

import com.andrew.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtistsFromAPI() : Response<ArtistList>
}