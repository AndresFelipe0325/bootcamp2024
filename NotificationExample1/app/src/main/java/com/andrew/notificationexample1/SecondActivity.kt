package com.andrew.notificationexample1

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class SecondActivity : AppCompatActivity() {
    private lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        result = findViewById(R.id.result_tv)
        receiveInput()
    }

    /** Method for receiving the reply action from the notification object*/
    private fun receiveInput(){
        /** Here we need to have the same key use for the reply action describe on the MainActivity
         * we get the result using the remoteInput.getResultsFromIntent and then we extract the
         * response through an object. Then display that result on a textview*/
        val REPLY_KEY = "reply_key"
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput != null){
            val inputString = remoteInput.getCharSequence(REPLY_KEY).toString()
            result.text = inputString

            /** Using the same config: channelId and notificationId (setted on MainActivity) we can
             * notify that we received the information set on the reply action*/
            val channelId = "com.andrew.notificationexample1.channel1"
            val notificationId = 25

            val repliedNotification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply received")
                .build()

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)
        }
    }
}