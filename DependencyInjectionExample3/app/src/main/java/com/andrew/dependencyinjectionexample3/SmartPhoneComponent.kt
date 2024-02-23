package com.andrew.dependencyinjectionexample3

import dagger.Component

/** In this class we add the module(s) of the classes we want to instantiate through Dagger**/
@Component(modules = [MemoryCardModule::class])
interface SmartPhoneComponent {
    fun getSmartPhone() : SmartPhone
}