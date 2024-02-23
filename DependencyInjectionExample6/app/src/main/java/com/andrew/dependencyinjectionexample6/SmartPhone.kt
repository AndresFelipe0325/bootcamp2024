package com.andrew.dependencyinjectionexample6

import android.util.Log
import javax.inject.Inject

class SmartPhone @Inject constructor(battery: Battery, simCard: SIMCard, memoryCard: MemoryCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailability()
        Log.i("myTag", "Smartphone constructed")
    }

    fun makeACallWithRecording(){
        Log.i("myTag", "Calling...")
    }
}