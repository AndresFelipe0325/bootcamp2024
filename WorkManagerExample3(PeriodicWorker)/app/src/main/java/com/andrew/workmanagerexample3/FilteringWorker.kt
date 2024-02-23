package com.andrew.workmanagerexample3

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class FilteringWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            for (i in 0 ..400) {
                Log.i("MyTag", "Filtering $i")
            }
        } catch (e: Exception){
            return Result.failure()
        }
        return Result.success()
    }
}