package com.andrew.dependencyinjectionexample6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var smartPhone: SmartPhone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** We need to do the following steps:
         * TODO: We have to replace the method used from create() to builder()
         * TODO: We need to pass the instance of the module which have a associated state
         * TODO: Call the function build() and then inject**/
        DaggerSmartPhoneComponent.builder()
            .memoryCardModule(MemoryCardModule(100))
            .build()
            .inject(this)

        smartPhone.makeACallWithRecording()

    }
}