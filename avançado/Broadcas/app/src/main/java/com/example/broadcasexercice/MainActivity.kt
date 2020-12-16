package com.example.broadcasexercice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSDKLass26.setOnClickListener {
            val intent = Intent(this, RebootBroadcast::class.java).also {
                it.action = "MY_CUSTOM_ACTION_ON_MANIFEST"
                sendBroadcast(it)
            }
        }

        buttonSDKGreaterOrEqual26.setOnClickListener {

        }

        buttonOpenApp.setOnClickListener {

        }
    }
}