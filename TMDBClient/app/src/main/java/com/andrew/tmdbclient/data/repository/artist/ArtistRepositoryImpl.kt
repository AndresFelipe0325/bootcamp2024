package com.andrew.tmdbclient.data.repository.artist

import android.util.Log
import com.andrew.tmdbclient.data.model.artist.Artist
import com.andrew.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.andrew.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.andrew.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.andrew.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtists = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtists)
        artistCacheDataSource.saveArtistsToCache(newListOfArtists)
        return newListOfArtists
    }

    suspend fun getArtistsFromAPI() : List<Artist>{
        lateinit var artistsList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtistsFromAPI()
            val body = response.body()
            if(body != null){
                artistsList = body.artists
            }
        }
        catch (exception: Exception){
            Log.i("MyTag", "getArtistsFromAPI -> exception: ${exception.message}")
        }
        return artistsList
    }

    suspend fun getArtistsFromDB() : List<Artist>{
        lateinit var artistsList: List<Artist>
        try {
            artistsList = artistLocalDataSource.getArtistsFromDB()
        }
        catch (exception: Exception){
            Log.i("MyTag", "getArtistsFromAPI -> exception: ${exception.message}")
        }
        if(artistsList.isNotEmpty()){
            return artistsList
        }
        else {
            artistsList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistsList)
        }
        return artistsList
    }

    suspend fun getArtistsFromCache() : List<Artist>{
        lateinit var artistsList: List<Artist>
        try {
            artistsList = artistCacheDataSource.getArtistsFromCache()
        }
        catch (exception: Exception){
            Log.i("MyTag", "getArtistsFromAPI -> exception: ${exception.message}")
        }
        if(artistsList.isNotEmpty()){
            return artistsList
        }
        else {
            artistsList = getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistsList)
        }
        return artistsList
    }
}