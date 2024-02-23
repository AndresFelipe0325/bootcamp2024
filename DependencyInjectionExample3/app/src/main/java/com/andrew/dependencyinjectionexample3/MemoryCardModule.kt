package com.andrew.dependencyinjectionexample3

import dagger.Module
import dagger.Provides


/** We use special annotations like @Module and @provides to indicate to Dagger which class instance
 * we want to instantiate.**/
@Module
class MemoryCardModule {

    @Provides
    fun providesMemoryCard() : MemoryCard {
        return MemoryCard()
    }
}