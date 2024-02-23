package com.andrew.dependencyinjectionexample5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    /** FIELD INJECTION **/
    @Inject lateinit var smartPhone: SmartPhone
    @Inject lateinit var SIMCard: SIMCard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Way to inject the dependencies without creating getters for each one of the components
        DaggerSmartPhoneComponent.create().inject(this)

        //smartPhone.makeACallWithRecording()

        SIMCard.getConnection()

        //Dependency injection using getters for the dependencies
        /*DaggerSmartPhoneComponent.create()
            .getSmartPhone()
            .makeACallWithRecording()*/
    }
}