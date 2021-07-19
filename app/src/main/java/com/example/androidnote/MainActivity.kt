package com.example.androidnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.androidnote.databinding.ActivityMainBinding
import com.example.androidnote.storage.StorageCheckActivity
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
        binding.moveStorageBtn.setOnClickListener{
            val intent = Intent(this, StorageCheckActivity::class.java)
            startActivity(intent)

        }


    }
}