package com.andrew.dependencyinjectionexample5

import dagger.Module
import dagger.Provides

@Module
class ServiceProviderModule {

    @Provides
    fun providesServiceProvider() : ServiceProvider{
        return ServiceProvider()
    }
}