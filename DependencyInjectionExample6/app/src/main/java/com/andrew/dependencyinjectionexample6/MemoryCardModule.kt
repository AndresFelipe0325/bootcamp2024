package com.andrew.dependencyinjectionexample6

import android.util.Log
import dagger.Module
import dagger.Provides


/** In this example we are going to add a state to the module (a memorySize).
 * TODO: Add the parameter in the MemoryCardModule constructor and put a log to see the result on the providesMemoryCard() method **/
@Module
class MemoryCardModule(private val memorySize: Int) {

    @Provides
    fun providesMemoryCard() : MemoryCard {
        Log.i("myTag", "Size of the memory is $memorySize")
        return MemoryCard()
    }
}