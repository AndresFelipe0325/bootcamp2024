package com.andrew.dependencyinjectionexample6

import android.util.Log

class MemoryCard {
    init {
        Log.i("myTag", "MemoryCard constructed")
    }

    fun getSpaceAvailability(){
        Log.i("myTag", "Memory space available")
    }
}