package com.andrew.dependecyinjectionexample1

import android.util.Log

/** This is an example class of depency-dependent relationship.
 * Here Smartphone class is a dependent class of Battery, SIMCard and MemoryCard classes, but
 * them instead are the dependency of Smartphone (direct dependency).
 * In this particular case SIMCard is dependent of another class called ServiceProvider turning
 * ServiceProvider as a dependency of SIMCard class and indirect dependency of Smartphone class.**/
class Smartphone(battery: Battery, simCard: SIMCard1, memoryCard: MemoryCard) {

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