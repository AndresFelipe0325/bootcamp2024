package com.andrew.tmdbclient.data.model.artist


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
/** We are going to use the data classes: Artist, Movie and TvShow as Room's entities **/
@Entity(tableName = "popular_artists")
data class Artist(

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("profile_path")
    val profilePath: String?
)