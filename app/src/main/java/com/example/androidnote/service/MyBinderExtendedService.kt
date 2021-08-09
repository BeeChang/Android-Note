package com.example.androidnote.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyBinderExtendedService : Service() {

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e("MyBinderExtendedService", "1");
        return MyBinder()
    }

    inner class MyBinder : Binder() {
//        MyBinderExtendedService getService() { // 서비스 객체를 리턴
//            return MyBinderExtendedService.this;
//        }

        val service: MyBinderExtendedService
            get() = this@MyBinderExtendedService
    }

}