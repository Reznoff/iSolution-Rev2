package com.example.kemalmaulana.isolution.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.view.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
//        super.onMessageReceived(remoteMessage)
//        Log.d("FCM", "From: ${remoteMessage?.from}")

        kehadiranNotification(remoteMessage?.notification?.title, remoteMessage?.notification?.body)
//        Log.d("FNotif", "Notification : ${remoteMessage?.notification?.body}")

//        if (remoteMessage != null) {
//            if(remoteMessage.data.isNotEmpty()) {
//                Log.d("FCM", "Message Data Payload :${remoteMessage.data}")
//                //do some shit
//            }
//        }
//        if(remoteMessage?.notification != null) {
//            Log.d("FCM", "Message notification body :${remoteMessage?.notification}")
//        }
    }

//    override fun onMessageSent(p0: String?) {
//        super.onMessageSent(p0)
//    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
//        Log.d("FCMToken", token)

    }

    fun kehadiranNotification(title: String?, messageBody: String?) {
        val notifyId = 1
        val channelId = "kehadiran_notification"
        val name = "Status Kehadiran"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel  = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH)
            val notification: Notification? = NotificationCompat.Builder(this, channelId)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setSmallIcon(R.drawable.icon_pena)
                    .setShowWhen(true)
                    .setChannelId(channelId)
                    .build()

            val mNotificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.createNotificationChannel(channel)
//            mNotificationManager.notify(notifyId, notification)

            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addNextIntent(Intent(this, MainActivity::class.java))
            val resultPendingIntent: PendingIntent? = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            notification?.contentIntent = resultPendingIntent
            mNotificationManager.notify(notifyId, notification)
        } else {
            val notification: Notification? = NotificationCompat.Builder(this)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setSmallIcon(R.drawable.icon_pena)
                    .setShowWhen(true)
                    .build()

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addNextIntent(Intent(this, MainActivity::class.java))
            val resultPendingIntent: PendingIntent? = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            notification?.contentIntent = resultPendingIntent
            notificationManager.notify(notifyId, notification)
        }

    }


}