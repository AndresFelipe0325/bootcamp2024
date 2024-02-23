package com.example.asyncawaitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** This way is called sequential decomposition **/
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("MyTag", "Calculation started ...")
            Log.i("MyTag", "Sequential decomposition")
            val stock1 = getStock1()
            val stock2 = getStock2()
            val total = stock1 + stock2
            Log.i("MyTag", "Total is $total")
        }

        /** Method 1: Parallel decomposition using async{} and
         * await() on IO thread**/
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("MyTag", "Calculation started ...")
            Log.i("MyTag", "Parallel decomposition")
            val stock1 = async { getStock1() }
            val stock2 = async { getStock2() }
            val total = stock1.await() + stock2.await()
            Log.i("MyTag", "Total is $total")
        }

        /** Method 2: Parallel decomposition using async{} and
         * await() on Main thread **/
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("MyTag", "Calculation started ...")
            Log.i("MyTag", "Parallel decomposition")
            val stock1 = async(Dispatchers.IO) { getStock1() }
            val stock2 = async(Dispatchers.IO) { getStock2() }
            val total = stock1.await() + stock2.await()
            Toast.makeText(applicationContext, "Total is $total", Toast.LENGTH_LONG).show()
            Log.i("MyTag", "Total is $total")
        }
    }
}

private suspend fun getStock1() : Int {
    delay(10000)
    Log.i("MyTag", "stock 1 returned")
    return 55000
}

private suspend fun getStock2() : Int {
    delay(8000)
    Log.i("MyTag", "stock 2 returned")
    return 35000
}