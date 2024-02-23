package com.andrew.dependencyinjectionexample5

import android.util.Log

class SIMCard (private val serviceProvider: ServiceProvider) {

    init {
        Log.i("myTag", "SIM card constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}