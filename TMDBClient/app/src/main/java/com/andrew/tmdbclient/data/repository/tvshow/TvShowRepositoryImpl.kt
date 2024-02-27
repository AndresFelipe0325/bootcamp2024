package com.andrew.tmdbclient.data.repository.tvshow

import android.util.Log
import com.andrew.tmdbclient.data.model.tvshow.TvShow
import com.andrew.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.andrew.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.andrew.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.andrew.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfTvShow = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShow)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShow)
        return newListOfTvShow
    }

    suspend fun getTvShowsFromAPI() : List<TvShow>{
        lateinit var tvShowsList: List<TvShow>
        try{
            val response = tvShowRemoteDataSource.getTvShowsFromAPI()
            val body = response.body()
            if(body != null){
                tvShowsList = body.tvShows
            }
        }
        catch (e: Exception){
            Log.i("MyTag", "getTvShowsFromAPI -> exception: ${e.message}")
        }
        return tvShowsList
    }

    suspend fun getTvShowsFromDB() : List<TvShow> {
        lateinit var tvShowsList: List<TvShow>
        try{
            tvShowsList = tvShowLocalDataSource.getTvShowsFromDB()
        }
        catch (e: Exception){
            Log.i("MyTag", "getTvShowsFromAPI -> exception: ${e.message}")
        }

        if(tvShowsList.isNotEmpty()){
            return tvShowsList
        }
        else{
            tvShowsList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowsList)
        }
        return tvShowsList
    }

    suspend fun getTvShowsFromCache() : List<TvShow> {
        lateinit var tvShowsList: List<TvShow>
        try{
            tvShowsList = tvShowCacheDataSource.getTvShowsFromCache()
        }
        catch (e: Exception){
            Log.i("MyTag", "getTvShowsFromAPI -> exception: ${e.message}")
        }

        if(tvShowsList.isNotEmpty()){
            return tvShowsList
        }
        else{
            tvShowsList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowsList)
        }
        return tvShowsList
    }
}