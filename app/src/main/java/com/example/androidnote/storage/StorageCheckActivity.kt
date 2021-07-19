package com.example.androidnote.storage

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidnote.R
import com.example.androidnote.databinding.ActivityStorageCheckBinding


class StorageCheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStorageCheckBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_storage_check)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_storage_check)
        binding.activity

        binding.internalCacheTextView.text = cacheDir.absolutePath
        binding.internalFilesDirTextView.text = filesDir.absolutePath

        binding.externalDcimTextView.text =
            getExternalFilesDir(Environment.DIRECTORY_DCIM).toString()
//        binding.externalExterTextView.text = getExternalFilesDir().toString()

    }


}