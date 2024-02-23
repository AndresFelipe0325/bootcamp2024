package com.andrew.dependecyinjectionexample1

import android.util.Log

class Battery {

    init {
        Log.i("myTag", "Battery constructed")
    }
    
    fun getPower(){
        Log.i("myTag", "Bateery power is available ")
    }
}