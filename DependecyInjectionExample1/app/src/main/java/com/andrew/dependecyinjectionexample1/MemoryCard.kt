package com.andrew.dependecyinjectionexample1

import android.util.Log

class MemoryCard {
    init {
        Log.i("myTag", "MemoryCard constructed")
    }

    fun getSpaceAvailability(){
        Log.i("myTag", "Memory space available")
    }
}