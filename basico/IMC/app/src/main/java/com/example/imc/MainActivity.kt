package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalc.setOnClickListener {
            val weight = numberWeight.text.toString().toDouble()
            val height = numberHeight.text.toString().toDouble()

            resultText.text = (weight / (height * height)).toString()
        }

    }
}