package com.example.intent_implicito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val myIntent = Intent("ACTIVITY_2_INTENT_ACTION")

            if (myIntent.resolveActivity(packageManager) != null) {
                startActivity(myIntent)
            } else {
                Toast.makeText(this, "Não foi encontrado uma activity para o " +
                        "intent filter escolhido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}