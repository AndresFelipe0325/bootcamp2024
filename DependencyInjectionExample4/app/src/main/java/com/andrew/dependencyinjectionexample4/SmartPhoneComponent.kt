package com.andrew.dependencyinjectionexample4

import dagger.Component

/** We added the NCBattery module to the component **/
@Component(modules = [MemoryCardModule::class, NCBatteryModule::class])
interface SmartPhoneComponent {
    fun getSmartPhone() : SmartPhone
}