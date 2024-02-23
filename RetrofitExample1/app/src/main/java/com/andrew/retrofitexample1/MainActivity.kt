package com.andrew.retrofitexample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)

        /** First we need to get a instance of the service interface.
         * we pass the name of the interface through the create method()*/
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        /*getRequestWithQueryParameters()
        getRequestWithPathParameters()*/
        uploadAlbum()

    }

    private fun uploadAlbum() {
        val album = AlbumItem(0, "My title", 3)
        val postResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this){
            val receivedAlbumItem = it.body()
            val result: String = "Album id: ${receivedAlbumItem?.id} \n" +
                    " Album title: ${receivedAlbumItem?.title} \n" +
                    " Album userId: ${receivedAlbumItem?.userId} \n\n\n "
            textView.append(result)
        }
    }

    /** This is an example of how we can request information from endpoint with query parameteres*/
    private fun getRequestWithQueryParameters(){
        /** Getting the response from retrofit using liveData coroutine */
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }

        responseLiveData.observe(this) {
            val albumsList = it.body()?.listIterator()
            if(albumsList != null){
                while(albumsList.hasNext()) {
                    val albumItem = albumsList.next()
                    Log.i("myTag", "onCreate -> AlbumId: ${albumItem.id}")
                    val result: String = "Album id: ${albumItem.id} \n" +
                            " Album title: ${albumItem.title} \n" +
                            " Album userId: ${albumItem.userId} \n\n\n "
                    textView.append(result)
                }
            }
        }
    }

    /** This is an example of how we can request information from endpoint with path parameteres*/
    private fun getRequestWithPathParameters(){
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this){
            Toast.makeText(this, "Album title: ${it.body()?.title}", Toast.LENGTH_LONG).show()
        }
    }
}