package com.andrew.tmdbclient.data.api

import com.andrew.tmdbclient.data.model.artist.ArtistList
import com.andrew.tmdbclient.data.model.movie.MovieList
import com.andrew.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/** This interface is going to manage the request we're going to do by Retrofit**/
interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String) : Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String) : Response<TvShowList>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String) : Response<ArtistList>
}