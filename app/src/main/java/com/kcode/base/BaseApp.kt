package com.kcode.base

import android.util.Log
import androidx.multidex.MultiDexApplication
//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : MultiDexApplication() {
    companion object {
        const val TAG = "BaseApp"
    }

    override fun onCreate() {
        super.onCreate()
        initFCM()
    }

    private fun initFCM() {

//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.e(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            Log.e(TAG, "FCM token $token")
//        })
    }
}