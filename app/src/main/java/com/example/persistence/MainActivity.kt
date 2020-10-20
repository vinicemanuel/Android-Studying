package com.example.persistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val internalFileName = "internalFile"
    val externalFileName = "externalFile"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.readButton.setOnClickListener {
            val name = this.readInternal()
            val age = this.readExternal()
        }

        this.writeButton.setOnClickListener {
            this.writeInternal(this.nameEditText.text.toString())
            this.writeExternal(this.ageEditText.text.toString())
        }
    }

    private fun writeInternal(value: String) {

    }

    private fun readInternal(): String {
        return ""
    }

    private fun writeExternal(value: String) {

    }

    private fun readExternal(): String {
        return ""
    }
}