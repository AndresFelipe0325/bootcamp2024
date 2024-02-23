package com.andrew.dependencyinjectionexample4

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NCBatteryModule {

    /** As it is this method for instantiating the Battery interface should work but, we used the
     * @Inject annotation on the NickelCadmium class and inject the constructor directly so we can
     * change this method.
     *
     * TODO: Use a better way to return an instance of a Battery interface (code below of the current method)**/
    /*@Provides
    fun providesNCBattery() : Battery{
        return NickelCadmiumBattery()
    }*/

    /** This is a better way to instantiate the Battery interface using the NickelCadmium class.
     * This is a better way but no the more concise. the code below shows how to do it that way**/
    /*@Provides
    fun providesNCBattery(nickelCadmiumBattery: NickelCadmiumBattery) : Battery{
        return nickelCadmiumBattery
    }*/

    /** More concise implementation: Because we are injecting the NickelCadmiumBattery class
     * constructor:
     *
     * TODO: Convert the class into an abstract class and do the same for the provides method.
     * TODO: change the annotation from @Provides to @Binds (abstract function do not allow @Provides annotation) **/
    @Binds
    abstract fun bindsNCBattery(nickelCadmiumBattery: NickelCadmiumBattery) : Battery
}