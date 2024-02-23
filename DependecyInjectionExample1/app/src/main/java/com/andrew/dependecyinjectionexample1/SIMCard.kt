package com.andrew.dependecyinjectionexample1

import android.util.Log

/** This class has a dependency of ServiceProvider class. We can use the Contructor injection as we
 * saw on the Smartphone instance used on the MainActivity.kt or we can do a METHOD INJECTION as below**/
class SIMCard2 {

    private lateinit var serviceProvider: ServiceProvider

    init {
        Log.i("myTag", "SIM card constructed")
    }

    /** METHOD INJECTION
     * we set the dependency instance through the method**/
    fun setServiceProvider(serviceProvider: ServiceProvider){
        this.serviceProvider = serviceProvider
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}

/** FIELD INJECTION
 * Taking the SIMCard2 example above we can change the visibility modifier of the field from private
 * to public and then inject the dependency through setting the field value on the instance
 * **/
class SIMCard3 {
    lateinit var serviceProvider: ServiceProvider

    init {
        Log.i("myTag", "SIM card constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}



//This was the old class definition with the constructor dependency
class SIMCard1(private val serviceProvider: ServiceProvider) {

    init {
        Log.i("myTag", "SIM card constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}