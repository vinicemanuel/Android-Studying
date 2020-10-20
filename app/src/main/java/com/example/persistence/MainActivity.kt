package com.example.persistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val internalFileName = "internalFile.txt"
    private val externalFileName = "externalFile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.readButton.setOnClickListener {
            val name = this.readInternal()
            val age = this.readExternal()

            val result = "$name and $age"
            this.resultText.text = result
        }

        this.writeButton.setOnClickListener {
            this.writeInternal(this.nameEditText.text.toString())
            this.writeExternal(this.ageEditText.text.toString())
        }
    }

    private fun writeInternal(value: String) {
        val path = this.filesDir
        Log.d("main", path.toString())
        this.openFileOutput(this.internalFileName, Context.MODE_PRIVATE).use {
            it.write(value.toByteArray())
        }
    }

    private fun readInternal(): String {

        val fileInputStream = openFileInput(this.internalFileName)

        val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }

        return stringBuilder.toString()
    }

    private fun writeExternal(value: String) {
        val path = this.getExternalFilesDir(null)
    }

    private fun readExternal(): String {
        return ""
    }
}