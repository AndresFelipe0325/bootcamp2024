package com.andrew.notificationexample1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MainActivity : AppCompatActivity() {
    private lateinit var sendButton: Button
    /** First we need to create a notification channel */
    private val channelId: String = "com.andrew.notificationexample1.channel1"
    /** Create a notification manager instance - required for creating a notification channel
     * (declaration of variable type var because we're going to use the SystemService so this
     * could be reassign */
    private var notificationManager: NotificationManager? = null
    /** In order to create the reply action on notification we need to have a variable with the result key
     * which is going to be used for the action.*/
    private val REPLY_KEY = "reply_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NotificationManager assignation
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        /** We need to create the channel before any notification has been sent that's why we do it
         * here on the onCreate() method*/
        createNotificationChannel(channelId, "DemoChannel", "this is a demo")

        sendButton = findViewById(R.id.send_button)
        sendButton.setOnClickListener {
            displayNotification()
        }
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String ){
        /** We add a validation for Android version equals or above android 8 (SDK 26) because of
         * compatibility of the notification functionality*/
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Notification channel has an importance level
            val importance = NotificationManager.IMPORTANCE_HIGH
            // Create a channel instance
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            // pass the channel instance to the manager and create the channel.
            notificationManager?.createNotificationChannel(channel)
        }
    }

    /** Notification in its most basic form displays an icon, a title and a description*/
    private fun displayNotification() {
        // first: Create a channelId (this could be any value we want)
        val notificationId = 25
        /** Before creating the notification object we are going to create the action we want to execute
         * when the user tap the notification. We need to create a intent call to the activity or fragment
         * we want to go and then create a pendingIntent a class helps to declare an intent that is going
         * to be used in the future */
        val tapResultIntent = Intent(this, SecondActivity::class.java)/*.apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT // we could apply as many flags as we need
        }*/
        val pendingIntentTap = PendingIntent.getActivity(
            this,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        /** We can create option on the notification adding action to the notification object. To do so
         * we need to create an intent declaring what to do once the user tap the action (normally going to
         * an activity or fragment), then creating the pendingintent for it and finally declaring an action
         * using the notificationCompat as shown below */
        // Action: going to detailsActivity
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        val pendingIntentDetails = PendingIntent.getActivity(
            this,
            0,
            detailsIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action1 = NotificationCompat.Action.Builder(
            android.R.drawable.ic_dialog_info, //Icon
            "Details", // title
            pendingIntentDetails // Intent to show
        ).build()

        // Action: going to detailsActivity
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        val pendingIntentSettings = PendingIntent.getActivity(
            this,
            0,
            settingsIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action2 = NotificationCompat.Action.Builder(
            android.R.drawable.ic_dialog_info, //Icon
            "Settings", // title
            pendingIntentSettings // Intent to show
        ).build()

        // Action: going to reply action (SecondActivity)
        val remoteInput: RemoteInput = RemoteInput.Builder(REPLY_KEY).run {
            setLabel("Insert your name") // Hint for the reply action
            build()
        }

        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
            android.R.drawable.ic_dialog_info,
            "Reply", // title for action
            pendingIntentTap // Using the secondActivity intent to display the reply
        ).addRemoteInput(remoteInput).build()

        // Create the notification object through notificationCompat
        val notification = NotificationCompat.Builder(this@MainActivity, channelId)
            .setContentTitle("Demo title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(action1)
            .addAction(action2)
            .addAction(replyAction)
            //.setContentIntent(pendingIntentTap)// Adding the pendingIntent on the notification object properties
            .build()
        notificationManager?.notify(notificationId, notification)
    }
}