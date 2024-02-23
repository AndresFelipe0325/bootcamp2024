package com.andrew.dependencyinjectionexample5

import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule {

    @Provides
    fun providesMemoryCard(): MemoryCard{
        return MemoryCard()
    }
}