package com.developer_awul2.intentservicebroadcastandall91to918.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.developer_awul2.intentservicebroadcastandall91to918.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForegroundService: Service() {

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

        startForegroundService()


      //  return Service.START_STICKY
        return Service.START_NOT_STICKY
    }




    @SuppressLint("ForegroundServiceType")
    private fun startForegroundService() {

        val channelId = "channel_id_foreground"
        val myChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(channelId, "Foreground Service", NotificationManager.IMPORTANCE_HIGH)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifManager.createNotificationChannel(myChannel)
        val notifBuilder: Notification.Builder = Notification.Builder(this, channelId).apply {
            setContentText("Service is running")
            setContentTitle("Service Enabled")
            setSmallIcon(R.mipmap.ic_launcher)
        }

        startForeground(99, notifBuilder.build())

    } // startForegroundService



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