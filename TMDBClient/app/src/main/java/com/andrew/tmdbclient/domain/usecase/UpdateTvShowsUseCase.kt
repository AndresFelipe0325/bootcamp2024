package com.andrew.tmdbclient.domain.usecase

import com.andrew.tmdbclient.data.model.tvshow.TvShow
import com.andrew.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowsUseCase(private val tvShowRepository: TvShowRepository) {

    suspend fun execute() : List<TvShow>? = tvShowRepository.updateTvShows()
}