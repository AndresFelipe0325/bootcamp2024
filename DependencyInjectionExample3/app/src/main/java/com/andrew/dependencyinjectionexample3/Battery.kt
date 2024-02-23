package com.andrew.dependencyinjectionexample3

import android.util.Log
import javax.inject.Inject

/** In order to inject this class the steps are: put the constructor visible using constructor keyword
 * and annotated with the @inject**/
class Battery @Inject constructor(){

    init {
        Log.i("myTag", "Battery constructed")
    }

    fun getPower(){
        Log.i("myTag", "Bateery power is available ")
    }
}