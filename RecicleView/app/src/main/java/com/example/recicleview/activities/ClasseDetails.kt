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

    private lateinit var classe: Classe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)
        
        intent.getParcelableExtra<Classe>(MainActivity.MAIN_ACTIVITY_RESULT_ID)?.let {
            self.nameEditText.setText(it.name)
            self.yearEditText.setText(it.ano.toString())
            classe = it
        }

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

        if (self.classe == null) {

            val newClasse = Classe(name, year)
            self.saveClasse(newClasse)
        } else {
            self.classe!!.name = name
            self.classe!!.ano = year
            self.saveClasse(self.classe!!)
        }
    }

    private fun saveClasse(classe: Classe) {

        val returnIntent = Intent()
        returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_RESULT_ID, classe)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}