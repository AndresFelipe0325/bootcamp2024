package com.andrew.dependencyinjectionexample3

import android.util.Log

/** For the purpose to use annotation @module from Dagger (This annotation is used when we cannot
 * access the definition of the class to add annotation like @Inject), we removed the annotation
 * and the visible constructor. Then a MemoryCardModule class was created for injecting this class
 * through Dagger**/
class MemoryCard {
    init {
        Log.i("myTag", "MemoryCard constructed")
    }

    fun getSpaceAvailability(){
        Log.i("myTag", "Memory space available")
    }
}