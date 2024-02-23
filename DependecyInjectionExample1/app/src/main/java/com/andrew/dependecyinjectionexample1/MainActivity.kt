package com.andrew.dependecyinjectionexample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** CONSTRUCTOR INJECTION WAY 1
         * Happens when you inject the dependencies direclty in the constructor of the class
         * like the example below **/
        val battery = Battery()
        val memoryCard = MemoryCard()
        val serviceProvider = ServiceProvider()
        val simCard = SIMCard1(serviceProvider)
        val smartphone = Smartphone(battery, simCard, memoryCard)
        smartphone.makeACallWithRecording()

        /** CONSTRUCTOR INJECTION WAY 2**/
        Smartphone(Battery(), SIMCard1(ServiceProvider()), MemoryCard())
            .makeACallWithRecording()

        /** METHOD INJECTION **/
        val simCard2 = SIMCard2()
        simCard2.setServiceProvider(ServiceProvider())

        /** FIELD INJECTION **/
        val simCard3 = SIMCard3()
        simCard3.serviceProvider = ServiceProvider()

    }
}