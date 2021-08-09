package com.example.androidnote.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("MyService", Thread.currentThread().name)
        Toast.makeText(this, "MyService onStartCommand", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.e("MyService", "onCreate")

        Toast.makeText(this, "MyService onCreate", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onDestroy() {
        Log.e("MyService", "onDestroy")
        Toast.makeText(this, "MyService onDestroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }


}