package com.andrew.dependencyinjectionexample6

import android.util.Log
import javax.inject.Inject

class NickelCadmiumBattery @Inject constructor(): Battery {
    override fun getPower() {
        Log.i("myTag", "Power from NickelCadmiumBattery")
    }
}