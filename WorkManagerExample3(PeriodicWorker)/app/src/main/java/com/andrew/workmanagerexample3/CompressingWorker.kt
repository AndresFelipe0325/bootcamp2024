package com.andrew.workmanagerexample3

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class CompressingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            for (i in 0 ..150) {
                Log.i("MyTag", "Compressing $i")
            }
        } catch (e: Exception){
            return Result.failure()
        }
        return Result.success()
    }
}