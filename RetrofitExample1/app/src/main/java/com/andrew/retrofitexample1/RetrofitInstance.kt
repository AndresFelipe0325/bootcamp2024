package com.andrew.retrofitexample1

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/** Retrofit instance to do all the request from network*/
class RetrofitInstance {

    companion object{
        private const val baseUrl = "https://jsonplaceholder.typicode.com"

        /** Applying an interceptor in order to catch the logs of network operations to find out what is going on*/
        private val interceptor = HttpLoggingInterceptor().apply {
            /** Depending of what are you looking for there are 3 levels: BASIC, BODY AND HEADERS*/
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                /** Adding timeouts to the connection with server */
                .connectTimeout(30, TimeUnit.SECONDS) // Time for establishing connection with server.
                .readTimeout(25, TimeUnit.SECONDS) // Maximum time gap between arrivals of two data packages while waiting for server response
                .writeTimeout(25, TimeUnit.SECONDS) //Maximum time gap between 2 data packages when sending them to the server
        }.build()

        fun getRetrofitInstance() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}