package com.andrew.tmdbclient.domain.repository

import com.andrew.tmdbclient.data.model.artist.Artist

interface ArtistRepository {

    suspend fun getArtists() : List<Artist>?

    suspend fun updateArtists() : List<Artist>?
}