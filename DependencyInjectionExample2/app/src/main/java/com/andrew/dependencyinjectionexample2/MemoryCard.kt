package com.andrew.dependencyinjectionexample2

import android.util.Log
import javax.inject.Inject

/** In order to inject this class the steps are: put the constructor visible using constructor keyword
 * and annotated with the @inject**/
class MemoryCard @Inject constructor() {
    init {
        Log.i("myTag", "MemoryCard constructed")
    }

    fun getSpaceAvailability(){
        Log.i("myTag", "Memory space available")
    }
}