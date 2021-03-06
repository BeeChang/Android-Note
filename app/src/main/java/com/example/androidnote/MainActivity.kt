package com.example.androidnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.androidnote.databinding.ActivityMainBinding
import com.example.androidnote.service.ServiceStartActivity
import com.example.androidnote.touchEvent.TouchEventCheckActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        clickEvent();

    }

    fun clickEvent(){
        binding.moveTouchEventBtn.setOnClickListener{
//            터치 이벤트로 이동
            val intent = Intent(this, TouchEventCheckActivity::class.java)
            startActivity(intent)

        }

        binding.svcBtn.setOnClickListener{
//            터치 이벤트로 이동
            val intent = Intent(this, ServiceStartActivity::class.java)
            startActivity(intent)

        }

    }
}