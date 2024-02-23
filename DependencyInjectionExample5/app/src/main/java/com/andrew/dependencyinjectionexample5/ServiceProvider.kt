package com.andrew.dependencyinjectionexample5

import android.util.Log

class ServiceProvider {

    init {
        Log.i("myTag", "Service provider constructed")
    }

    fun getServiceProvider(){
        Log.i("myTag", "Service provider connected")
    }
}