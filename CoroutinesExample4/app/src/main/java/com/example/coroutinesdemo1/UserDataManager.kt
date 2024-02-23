package com.example.coroutinesdemo1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager {

    suspend fun getTotalUserCount() : Int{
        var count = 0

        /** Unstructure concurrency:
         * This coroutine was created after his parent coroutine was created so it behaves separately
         * from parent that's why before the execution of its execution the main coroutine had finished
         * and the count value return was 0**/
        CoroutineScope(IO).launch {
            delay(1000)
            count = 50
        }

        /** Using async we can assure to return the specific value but, it is not a good way to
         * handle exceptions so this is not a recommended practice.**/
        val deferred = CoroutineScope(IO).async {
            delay(3000)
            return@async 70
        }

        //return count
        return count + deferred.await()
    }
}