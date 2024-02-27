package com.andrew.tmdbclient.domain.usecase

import com.andrew.tmdbclient.data.model.artist.Artist
import com.andrew.tmdbclient.domain.repository.ArtistRepository

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {

    suspend fun execute() : List<Artist>? = artistRepository.getArtists()
}