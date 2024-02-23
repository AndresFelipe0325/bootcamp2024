package com.andrew.dependencyinjectionexample2

import android.util.Log
import javax.inject.Inject


/** In order to inject this class the steps are: put the constructor visible using constructor keyword
 * and annotated with the @inject. if our constructor has parameters, those parameters need to be
 * inject as well.**/
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