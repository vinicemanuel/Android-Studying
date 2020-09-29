package com.example.startactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val value01 = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_VALUE01_EXTRA_ID, 0)
        val value02 = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_VALUE02_EXTRA_ID, 0)

        val returnIntent = Intent()
        returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_SUM_RESULT_EXTRA_ID, value01 + value02)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}