package com.andrew.dependencyinjectionexample5

import android.util.Log

class Battery {

    init {
        Log.i("myTag", "Battery constructed")
    }

    fun getPower(){
        Log.i("myTag", "Battery power is available ")
    }
}