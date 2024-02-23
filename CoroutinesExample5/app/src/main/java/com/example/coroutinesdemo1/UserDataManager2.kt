package com.example.coroutinesdemo1

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager2 {

    /** Structured concurrency: Using coroutinesScope (suspend function) instead of
     * CoroutineScope (Interface) -> guarantess the completion of all task (work) started by the
     * coroutines within the child scope before the return of the suspend function**/
    suspend fun getTotalUserCount():Int {
        var count = 0
        lateinit var deferred: Deferred<Int>
        /** The suspend function coroutineScope creates a child scope and guarantees the completion
         * of all task within the child scope before the return of the function**/
        coroutineScope {
            launch(IO) {
                delay(1000)
                count = 50
            }

            deferred = async(IO) {
                delay(3000)
                return@async 70
            }
        }
        return count + deferred.await()
    }
}