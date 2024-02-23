package com.andrew.dependencyinjectionexample6

import dagger.Binds
import dagger.Module

@Module
abstract class NCBatteryModule {

    @Binds
    abstract fun bindsNCBattery(nickelCadmiumBattery: NickelCadmiumBattery) : Battery
}