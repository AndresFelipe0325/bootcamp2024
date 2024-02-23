package com.andrew.dependencyinjectionexample5

import dagger.Module
import dagger.Provides

@Module
class SIMCardModule {

    @Provides
    fun providesSIMCard(serviceProvider: ServiceProvider) : SIMCard{
        return SIMCard(serviceProvider)
    }
}