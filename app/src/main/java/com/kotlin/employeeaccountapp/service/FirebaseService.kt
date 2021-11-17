package com.kotlin.employeeaccountapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kotlin.employeeaccountapp.R
import com.kotlin.employeeaccountapp.dashboard.view.DashboardActivity


class FirebaseService : FirebaseMessagingService() {
    val TAG = "FirebaseService"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")
        Log.d(TAG, "From: ${remoteMessage.from}")
        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            //Message Services handle notification
            val notification = NotificationCompat.Builder(this)
                .setContentTitle(remoteMessage.from)
                .setContentText(it.body)
                .setSmallIcon(R.drawable.user_image)
                .build()
            val manager = NotificationManagerCompat.from(applicationContext)
            manager.notify(/*notification id*/0, notification)
            it.body?.let { it1 -> showNotification(remoteMessage.from, it1) }
        }
        /* // Check if message contains a data payload.
         if (remoteMessage.data.isNotEmpty()) {
             Log.d(TAG, "Message data payload: ${remoteMessage.data}")

             if (remoteMessage.notification != null) {
                 // Since the notification is received directly from
                 // FCM, the title and the body can be fetched
                 // directly as below.
                 remoteMessage.notification!!.body?.let {
                     showNotification(
                         remoteMessage.notification!!.title,
                         it
                     )
                 };
             }
         }

         // Check if message contains a notification payload.
         remoteMessage.notification?.let {
             Log.d(TAG, "Message Notification Body: ${it.body}")
         }*/

    }

    private fun showNotification(title: String?, message: String) {
        // Pass the intent to switch to the MainActivity
        val intent = Intent(this, DashboardActivity::class.java)
        // Assign channel ID
        val channelId = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        var builder = NotificationCompat.Builder(
            applicationContext,
            channelId
        )
            .setSmallIcon(R.drawable.user_image)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.
        builder =
            builder.setContent(
                title?.let { getCustomDesign(it, message) }
            )
        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channelId, "web_app",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }
        notificationManager.notify(0, builder.build())
    }

    // Method to get the custom Design for the display of
    // notification.
    private fun getCustomDesign(title: String, message: String): RemoteViews {
        val remoteViews = RemoteViews(
            applicationContext.packageName,
            R.layout.notification
        )
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.drawable.user_image
        )
        return remoteViews
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
    }

}