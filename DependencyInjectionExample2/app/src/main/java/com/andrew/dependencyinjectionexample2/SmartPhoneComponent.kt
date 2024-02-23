package com.andrew.dependencyinjectionexample2

import dagger.Component

@Component
interface SmartPhoneComponent {
    fun getSmartPhone() : SmartPhone
}