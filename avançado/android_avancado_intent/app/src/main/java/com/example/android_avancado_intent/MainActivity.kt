package com.example.android_avancado_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonExplicitIntent.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        buttonImplicitIntent.setOnClickListener {
            val intent = Intent("MY_CUSTOM_ACTION")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}