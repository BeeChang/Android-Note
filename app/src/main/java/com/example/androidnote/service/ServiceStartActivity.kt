package com.example.androidnote.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.example.androidnote.R
import com.example.androidnote.databinding.ActivityServiceStartBinding
import java.lang.Exception

class ServiceStartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_service_start)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_start)

        init()
    }

    private fun init() {
        binding.start.setOnClickListener {
            startBasicService()
        }
        binding.stop.setOnClickListener {
            stopBasicService()
        }
        binding.forStart.setOnClickListener {
            startForegroundService()
        }
        binding.forStop.setOnClickListener {
            stopForegroundService()
        }

        binding.bindBinderExtended.setOnClickListener {
            bindBinderExtendedService()
        }
        binding.unbindBinderExtended.setOnClickListener {
            unbindBinderExtendedService()
        }
        binding.unbindBinderExtendedToast.setOnClickListener {
            binderExtendedServiceBinder?.run {
                service.showToast("Service is bound!")
            }
        }

        binding.bindMsgIpcService.setOnClickListener {
            bindMessengerService()
        }
        binding.unbindMsgIpcService.setOnClickListener {
            unbindMessengerService()
        }
        binding.showToastMsgIpc.setOnClickListener {
            showMessengerIPCServiceToast()
        }
        binding.invokeAddFunMsgIpc.setOnClickListener {
            invokeAddMessengerIPCService()
        }

    }




    //    IPC
    private val messengerIPCHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MyMessengerIPCService.MSG_ADD_RESPONSE ->
                    Toast.makeText(
                        this@ServiceStartActivity,
                        "Add response: ${msg.arg1}",
                        Toast.LENGTH_SHORT
                    ).show()
                else -> super.handleMessage(msg)
            }
        }
    }

    private val messengerIPCClient = Messenger(messengerIPCHandler)
    private var messengerIPCService: Messenger? = null
    private val messengerIPCServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messengerIPCService = Messenger(service).apply {
                send(Message.obtain(null, MyMessengerIPCService.MSG_BIND_CLIENT, 0, 0).apply {
                    replyTo = messengerIPCClient
                })
            }
            Toast.makeText(
                this@ServiceStartActivity,
                "MessengerIPCService - onServiceConnected",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            messengerIPCService = null
            Toast.makeText(
                this@ServiceStartActivity,
                "MessengerIPCService - onServiceDisconnected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    //   IPC ?????????
    private fun bindMessengerService() {
        Intent(this, MyMessengerIPCService::class.java).run {
            bindService(this, messengerIPCServiceConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private fun unbindMessengerService() {
        messengerIPCService?.send(
            Message.obtain(
                null,
                MyMessengerIPCService.MSG_UNBIND_CLIENT,
                0,
                0
            ).apply {
                replyTo = messengerIPCClient
            })
        unbindService(messengerIPCServiceConnection)
    }

    private fun showMessengerIPCServiceToast() {
        messengerIPCService?.send(
            Message.obtain(null, MyMessengerIPCService.MSG_SHOW_TOAST, 0, 0).apply {
                data = bundleOf(MyMessengerIPCService.MSG_TOAST_TEXT to "Messenger IPC Service!")
            })
    }

    private fun invokeAddMessengerIPCService() {
        messengerIPCService?.send(Message.obtain(null, MyMessengerIPCService.MSG_ADD_REQUEST, 5, 1))
    }
//    ---------------------------------------------------------------------------------------------


    private fun startBasicService() {
        Intent(this, MyService::class.java).run {
            startService(this)
        }
    }

    private fun stopBasicService() {
        Intent(this, MyService::class.java).run {
            stopService(this)
        }
    }


    private fun startForegroundService() {
        Intent(this, MyForegroundService::class.java).run {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) startForegroundService(this)
            else startService(this)
        }
    }

    private fun stopForegroundService() {
        Intent(this, MyForegroundService::class.java).run {
            stopService(this)
        }
    }

    private fun bindBinderExtendedService() {
        Intent(this, MyBinderExtendedService::class.java).run {
            bindService(this, binderExtendedServiceConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private fun unbindBinderExtendedService() {
        try {
            unbindService(binderExtendedServiceConnection)
        } catch (e: Exception) {
            Log.e("1", e.toString())
        }
    }

//    ServiceConnection conn = new ServiceConnection() {
//        public void onServiceConnected(ComponentName name,
//            IBinder service) {
//// ???????????? ??????????????? ??? ???????????? ?????????
//// ????????? ????????? ??????????????? ??????
//            MyBinder mb = (MyBinder) service;
//            ms = mb.getService(); // ???????????? ???????????? ????????? ????????????
//// ???????????? ????????? ??????????????? ??????
//            isService = true;
//        }
//        public void onServiceDisconnected(ComponentName name) {
//// ???????????? ????????? ????????? ??? ???????????? ?????????
//            isService = false;
//        }
//    };

    private val binderExtendedServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binderExtendedServiceBinder = service as MyBinderExtendedService.MyBinder
            Log.e("name", name.toString())
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binderExtendedServiceBinder = null
        }
    }

    //    ????????? ?????? ????????? ????????????
    private var binderExtendedServiceBinder: MyBinderExtendedService.MyBinder? = null
}