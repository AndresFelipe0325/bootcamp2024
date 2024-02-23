package com.andrew.workmanagerexample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView

    companion object{
        const val INPUT_KEY = "count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        button.setOnClickListener {
            setOneTimeWorkerRequest()
        }
    }

    /** Method for setting the worker request and launch it.
     * Using LiveData we can get the state of the oneTimeWorker**/
    private fun setOneTimeWorkerRequest(){
        val workManager = WorkManager.getInstance(applicationContext)
        /** We can add constraints to the WorkManager in order to fulfill certain conditions to
         * complete the WorkManager task like the code below. **/
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        /** Another thing you can add to the worker is input/output data. the code below shows
         * how to add input data into the worker request **/
        val data: Data = Data.Builder()
            .putInt(INPUT_KEY, 125)
            .build()
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        workManager.enqueue(uploadRequest)
        // LiveData
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this) {
                textView.text = it.state.name
                /** In order to receive the data sent by the worker, we can use the states of the
                 * worker itself. In this case we need to display the date when the worker has
                 * finished the task, so we add a validation to know when the worker has finished
                 * and then using a Toast Message we display the information.**/
                if(it.state.isFinished){
                    val date = it.outputData.getString(UploadWorker.OUTPUT_KEY)
                    Toast.makeText(applicationContext, date, Toast.LENGTH_LONG).show()
                }
            }
    }
}