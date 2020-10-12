package com.example.recicleview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recicleview.R
import kotlinx.android.synthetic.main.activity_class_details.*

class ClasseDetails : AppCompatActivity() {

    val self = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)

        self.saveButton.setOnClickListener {
            self.save()
        }
    }

    private fun save() {

        if (self.nameEditText.text.isBlank()) {
            //TODO: handle error
            return
        }

        if (self.yearEditText.text.isBlank()) {
            //TODO: handle error
            return
        }

        val name = self.nameEditText.text.toString()
        val year = self.yearEditText.text.toString().toInt()

        finish()
    }
}