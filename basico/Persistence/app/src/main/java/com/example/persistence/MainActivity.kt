package com.example.persistence

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import kotlinx.android.synthetic.main.activity_main.*

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
        this.openFileOutput(this.internalFileName, MODE_PRIVATE).use {
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

        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            if (this.isExternalStorageWritable()) {
                FileOutputStream(this.externalFileName).use { output ->
                    output.write(value.toByteArray())
                }
            }

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }

    private fun readExternal(): String {

        if (this.isExternalStorageReadable()) {
            FileInputStream(this.externalFileName).use { stream ->
                return stream.bufferedReader().use {
                    it.readText()
                }
            }
        }

        return ""
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}