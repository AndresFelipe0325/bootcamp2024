package com.andrew.dependencyinjectionexample4

import android.util.Log
import javax.inject.Inject

class SIMCard @Inject constructor(private val serviceProvider: ServiceProvider) {

    init {
        Log.i("myTag", "SIM card constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}