package com.example.thirdparties.notification

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "MyFirebaseMsgService"
        private lateinit var customKey : String
        private lateinit var customValue : String
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

        //Get the last key-value data, just for example
        for ((key, value) in remoteMessage.data) {
                customKey = key
                customValue = value
        }

        //sendNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        val intent = Intent(this, NotifyNotificationReceived::class.java)
        intent.putExtra("title", remoteMessage.notification?.title)
        intent.putExtra("body", remoteMessage.notification?.body)
        intent.putExtra("key", customKey)
        intent.putExtra("value", customValue)
        startActivity(intent)
    }

//    private fun sendNotification(messageTitle:String, messageBody: String) {
//        val intent = Intent(this, HomeActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//            PendingIntent.FLAG_ONE_SHOT)
//
//        val channelId = getString(R.string.default_notification_channel_id)
//        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val notificationBuilder = NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.drawable.ic_stat_ic_notification)
//            .setContentTitle(messageTitle)
//            .setContentText("$messageBody Key: $key Value: $value")
//            .setAutoCancel(true)
//            .setSound(defaultSoundUri)
//            .setContentIntent(pendingIntent)
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        val channel = NotificationChannel(channelId,
//            "Channel human readable title",
//            NotificationManager.IMPORTANCE_DEFAULT)
//        notificationManager.createNotificationChannel(channel)
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
//    }
}
