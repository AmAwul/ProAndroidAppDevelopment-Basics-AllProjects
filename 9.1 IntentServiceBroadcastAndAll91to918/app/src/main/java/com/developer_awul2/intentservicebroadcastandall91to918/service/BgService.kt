package com.developer_awul2.intentservicebroadcastandall91to918.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BgService: Service() {

    private val TAG = "BgService"

    var isBgSRunning = false

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        isBgSRunning = true
        var mJob: Job? = null
        mJob = CoroutineScope(Dispatchers.IO).launch {

            if (!isBgSRunning) {
                mJob?.cancel()
            }

            while (isBgSRunning) {
                Log.d(TAG, "onStartCommand: Bg Service Loop is Running…")
                delay(1000)
            }

        }


      //  return Service.START_STICKY
        return Service.START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        Log.d(TAG, "onRebind: ")
        super.onRebind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
        isBgSRunning = false
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.d(TAG, "onTaskRemoved: ")
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }

}