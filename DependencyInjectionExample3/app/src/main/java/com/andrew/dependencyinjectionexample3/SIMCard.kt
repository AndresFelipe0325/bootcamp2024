package com.andrew.dependencyinjectionexample3

import android.util.Log
import javax.inject.Inject

/** In order to inject this class the steps are: put the constructor visible using constructor keyword
 * and annotated with the @inject**/
class SIMCard @Inject constructor(private val serviceProvider: ServiceProvider) {

    init {
        Log.i("myTag", "SIM card constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}