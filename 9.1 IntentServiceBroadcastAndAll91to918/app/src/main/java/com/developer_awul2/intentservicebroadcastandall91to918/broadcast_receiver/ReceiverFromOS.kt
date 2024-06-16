package com.developer_awul2.intentservicebroadcastandall91to918.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.widget.Toast

class ReceiverFromOS: BroadcastReceiver() {

    private val TAG = "ReceiverFromOS"

    override fun onReceive(context: Context, intent: Intent?) {

        intent?.action?.let {
            when(it) {
                Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                    val isAirplaneMode: Boolean = isAirplaneModeOn(context)
                    Log.d(TAG, "onReceive: ${isAirplaneMode}")
                    Toast.makeText(context, "Airplane: ${isAirplaneMode}", Toast.LENGTH_SHORT).show()
                }
            }
        }

//        intent?.action?.let {
//            when(it) {
//                Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
//                    val isAirplaneMode: Boolean = isAirplaneModeOn(context)
//                    Log.d(TAG, "onReceive: ${isAirplaneMode}")
//                    Toast.makeText(context, "Airplane: ${isAirplaneMode}", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.System.getInt(context.contentResolver, Settings.System.AIRPLANE_MODE_ON, 0) != 0
    }


}