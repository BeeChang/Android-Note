package com.example.androidnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.androidnote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        clickEvent();

    }

    fun clickEvent(){
        binding.moveNoteBtn.setOnClickListener{
//            주제에 맞는 프로젝트로 이동

        }
    }
}