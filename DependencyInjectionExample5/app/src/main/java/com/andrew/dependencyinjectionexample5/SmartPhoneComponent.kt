package com.andrew.dependencyinjectionexample5

import dagger.Component


/** This is the interface we need for handling the injection of the SmartPhone class on the MainActivity
 * Here we're getting the instance of the class.
 *
 * In this specific example we are going to inject the SmartPhone instance and its dependencies through
 * field injection:
 * - Delete getSmartphone() method
 * - Create an abstract method called inject (we can call it as we want) which receives a reference
 * from the activity who needs it (if we need to inject the same component through severals activities or
 * fragments, we need to create an inject function for each one of them).**/
@Component(modules = [BatteryModule::class, MemoryCardModule::class, SIMCardModule::class, ServiceProviderModule::class])
interface SmartPhoneComponent {

    //fun getSmartPhone() : SmartPhone

    fun inject(mainActivity: MainActivity)
}