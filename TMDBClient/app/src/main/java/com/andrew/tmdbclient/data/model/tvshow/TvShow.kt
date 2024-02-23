package com.andrew.tmdbclient.data.model.tvshow


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/** We are going to use the data classes: Artist, Movie and TvShow as Room's entities **/
@Entity(tableName = "popular_tv_shows")
data class TvShow(

    @PrimaryKey
    @SerializedName("first_air_date")
    val firstAirDate: String?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?
)