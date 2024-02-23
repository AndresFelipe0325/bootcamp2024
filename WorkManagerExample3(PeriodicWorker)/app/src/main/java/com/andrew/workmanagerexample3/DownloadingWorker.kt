package com.andrew.workmanagerexample3

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DownloadingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            for (i in 0 ..10) {
                Log.i("MyTag", "Downloading $i")
            }
        } catch (e: Exception){
            return Result.failure()
        }
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US)
        val currentDate = sdf.format(Date())
        Log.i("MyTag", "Downloading -> finished execution: $currentDate")
        return Result.success()
    }
}