package com.andrew.tmdbclient.domain.usecase

import com.andrew.tmdbclient.data.model.tvshow.TvShow
import com.andrew.tmdbclient.domain.repository.TvShowRepository

class GetTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute() : List<TvShow>? = tvShowRepository.getTvShows()
}