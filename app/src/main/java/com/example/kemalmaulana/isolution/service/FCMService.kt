package com.example.kemalmaulana.isolution.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCM", "From: ${remoteMessage?.from}")
        if (remoteMessage != null) {
            if(remoteMessage.data.isNotEmpty()) {
                Log.d("FCM", "Message Data Payload :${remoteMessage.data}")
                //do some shit
            }
        }
        if(remoteMessage?.notification != null) {
            Log.d("FCM", "Message notification body :${remoteMessage?.notification}")
        }
    }

//    override fun onMessageSent(p0: String?) {
//        super.onMessageSent(p0)
//    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("FCMToken", token)

    }


}