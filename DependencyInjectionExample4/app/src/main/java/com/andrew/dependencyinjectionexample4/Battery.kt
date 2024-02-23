package com.andrew.dependencyinjectionexample4


/** In this example we are going to work instantiating and injecting interfaces through Dagger.
 * For doing that we need to change several parts of code:
 * - Change class to be an interface.
 * - Remove the @inject and constructor annotations
 * - Remove the init (an Interface cannot be initialize or have an initial state)
 * - Remove getPower() method implementation
 *
 * TODO: We need to create a class (in this case NickelCadmiumBattery) that implements this interface for providing this interface dependency as a Dagger module **/

interface Battery {

    fun getPower()
}