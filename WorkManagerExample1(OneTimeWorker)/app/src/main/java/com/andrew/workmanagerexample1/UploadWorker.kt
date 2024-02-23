package com.andrew.workmanagerexample1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Important: The Worker class depends on 2 parameters: instance of Context and an instance of
 * WorkerParameters **/
class UploadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object{
        const val OUTPUT_KEY = "success_date"
    }

    override fun doWork(): Result {
        try {
            /** Reading the input data set on the worker task request**/
            val count = inputData.getInt(MainActivity.INPUT_KEY, 0)
            for (i in 0 until count) {
                Log.i("MyTag", "uploading $i")
            }
        } catch (e: Exception){
            return Result.failure()
        }
        /** Creating an output data that is going to be sent back when the worker has finished its
         * task**/
        val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US)
        val currentDate = time.format(Date())
        val outputData: Data = Data.Builder()
            .putString(OUTPUT_KEY, currentDate)
            .build()
        // We need to pass that output data object to the success method as a parameter
        return Result.success(outputData)
    }
}