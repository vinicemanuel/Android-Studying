package com.example.recicleview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recicleview.R
import com.example.recicleview.models.Classe

class MainActivity : AppCompatActivity() {
    private val self = this

    val classlist = mutableListOf(
        Classe("turma 1", 2019),
        Classe("turma 2", 2019),
        Classe("turma 1", 2020),
        Classe("turma 2", 2020)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setupRecyclerview()
    }

    private fun setupRecyclerview() {

    }

}