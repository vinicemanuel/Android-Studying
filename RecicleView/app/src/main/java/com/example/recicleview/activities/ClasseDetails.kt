package com.example.recicleview.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recicleview.R
import com.example.recicleview.models.Classe
import kotlinx.android.synthetic.main.activity_class_details.*

class ClasseDetails : AppCompatActivity() {

    private var classe: Classe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)
        
        intent.getParcelableExtra<Classe>(MainActivity.MAIN_ACTIVITY_RESULT_ID)?.let {
            this.nameEditText.setText(it.name)
            this.yearEditText.setText(it.ano.toString())
            classe = it
        }

        this.saveButton.setOnClickListener {
            this.save()
        }
    }

    private fun save() {

        if (this.nameEditText.text.isBlank()) {
            //TODO: handle error
            return
        }

        if (this.yearEditText.text.isBlank()) {
            //TODO: handle error
            return
        }

        val name = this.nameEditText.text.toString()
        val year = this.yearEditText.text.toString().toInt()

        if (this.classe == null) {

            val newClasse = Classe(name, year)
            this.saveClasse(newClasse)
        } else {
            this.classe!!.name = name
            this.classe!!.ano = year
            this.saveClasse(this.classe!!)
        }
    }

    private fun saveClasse(classe: Classe) {

        val returnIntent = Intent()
        returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_RESULT_ID, classe)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}