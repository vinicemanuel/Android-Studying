package com.example.broadcastapptoopen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent(this, ManifestBroadcast::class.java).also {
                it.action = "MY_CUSTOM_ACTION_TO_OPEN_APP"
                sendBroadcast(it)
            }
        }
    }
}