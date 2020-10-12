package com.example.recicleview.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recicleview.R
import com.example.recicleview.models.Classe
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

        val newClasse = Classe(name, year)

        val returnIntent = Intent()
        returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_RESULT_ID, newClasse)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}