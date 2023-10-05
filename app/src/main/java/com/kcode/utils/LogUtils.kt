package com.kcode.utils

import android.util.Log

object LogUtils {
    fun showELog(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    fun showDLog(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun showWLog(tag: String, msg: String) {
        Log.w(tag, msg)
    }
}