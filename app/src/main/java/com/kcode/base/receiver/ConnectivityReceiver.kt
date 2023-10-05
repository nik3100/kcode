package com.kcode.base.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kcode.utils.NetworkHelper

class ConnectivityReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.onNetworkConnectionChanged(NetworkHelper(context!!).isNetworkConnected())
        }
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}