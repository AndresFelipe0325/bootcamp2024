package com.andrew.tmdbclient.data.repository.movie

import android.util.Log
import com.andrew.tmdbclient.data.model.movie.Movie
import com.andrew.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.andrew.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.andrew.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.andrew.tmdbclient.domain.repository.MovieRepository

/** This class depends on data source of information: Remote, local and cache
 * that's why we put them as dependencies (parameters) of the class**/
class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    /** We are going to define the methods we need for doing the 3 operations: getting data from
     * API(remote), local(DB) and cache. We declare them as suspend function because we're going to
     * use Coroutines.
     */
    suspend fun getMoviesFromAPI() : List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if(body != null){
                movieList = body.movies
            }
        }
        catch (exception: Exception){
            Log.i("MyTag", "getMoviesFromAPI -> exception: ${exception.message}")
        }
        return movieList
    }

    suspend fun getMoviesFromDB() : List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        }
        catch (exception: Exception){
            Log.i("MyTag", "getMoviesFromAPI -> exception: ${exception.message}")
        }
        if(movieList.isNotEmpty()){
            return movieList
        }
        else {
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    suspend fun getMoviesFromCache() : List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        }
        catch (exception: Exception){
            Log.i("MyTag", "getMoviesFromAPI -> exception: ${exception.message}")
        }
        if(movieList.isNotEmpty()){
            return movieList
        }
        else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}