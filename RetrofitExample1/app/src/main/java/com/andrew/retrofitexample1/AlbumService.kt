package com.andrew.retrofitexample1

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/** Interface to define the method we're going to use for requesting info from endpoint*/
interface AlbumService {

    /** Http get example request*/
    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>

    /** Http get example request with parameters like this /albums?userId=valueWeWant */
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int) : Response<Albums>

    /** Http get example request with parameters like this /albums/parameter */
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int) : Response<AlbumItem>

    /** When we use a Post request we're sending our object through the body of the request
     * that's why we have to use the annotation @body plus the parameter we want to send*/
    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumItem) : Response<AlbumItem>
}