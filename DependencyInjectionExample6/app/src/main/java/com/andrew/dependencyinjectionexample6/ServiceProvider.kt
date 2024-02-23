package com.andrew.dependencyinjectionexample6

import android.util.Log
import javax.inject.Inject

class ServiceProvider @Inject constructor(){

    init {
        Log.i("myTag", "Service provider constructed")
    }

    fun getServiceProvider(){
        Log.i("myTag", "Service provider connected")
    }
}