package com.andrew.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrew.tmdbclient.data.model.artist.Artist
import com.andrew.tmdbclient.data.model.movie.Movie
import com.andrew.tmdbclient.data.model.tvshow.TvShow

@Database(entities = [Movie::class, Artist::class, TvShow::class],
    version = 1,
    exportSchema = false)
abstract class TMDBDatabase : RoomDatabase(){

    abstract fun movieDao() : MovieDao

    abstract fun TvShowDao() : TvShowDao

    abstract fun artistDao() : ArtistDao
}