package com.andrew.dependencyinjectionexample4

import android.util.Log
import javax.inject.Inject

/** This new class that implements the Battery interface it needs to be marked with the @Inject
 * annotation and put its constructor visible as we did before with the other classes
 *
 * TODO: We have to create a module for Dagger to know this is a Battery dependency**/
class NickelCadmiumBattery @Inject constructor(): Battery {
    override fun getPower() {
        Log.i("myTag", "Power from NickelCadmiumBattery")
    }
}