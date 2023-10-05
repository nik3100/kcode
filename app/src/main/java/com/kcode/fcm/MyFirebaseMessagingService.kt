//package com.kcode.fcm
//
//import android.app.PendingIntent
//import android.content.Intent
//import android.os.Build
//import android.util.Log
//import androidx.core.app.NotificationCompat
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
//import com.kcode.R
//import com.kcode.ui.view.activity.SplashActivity
//import com.kcode.utils.NotificationBuilder
//import com.kcode.R
//
//class MyFirebaseMessagingService : FirebaseMessagingService() {
//    companion object {
//        const val TAG = "PushNotification"
//    }
//
//    override fun onNewToken(token: String) {
//        Log.d(TAG, "Refreshed token: $token")
//
//        // sendRegistrationToServer(token)
//    }
//
//    override fun onMessageReceived(message: RemoteMessage) {
//        Log.d(TAG, "${message.data["msg"]}")
//        val data =message.data
//        val body=data["body"].toString()
//        val body2=data["body2"].toString()
//        createNotification(body,body2)
//    }
//
//    private fun createNotification(body: String="", body2: String="") {
//
//        val intent = Intent(this, SplashActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//        } else {
//            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//        NotificationBuilder.buildNotification(
//            this,
//            R.drawable.ic_baseline_notifications_24,
//            body,
//            body2,
//            NotificationCompat.PRIORITY_DEFAULT,
//            pendingIntent
//        )
//
//    }
//}