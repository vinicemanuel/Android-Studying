package com.example.broadcasd_first_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent("MY_CUSTOM_ACTION_TO_OPEN_APP").also {
                it.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                sendBroadcast(it)
            }
        }
    }
}