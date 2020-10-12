package com.example.recicleview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recicleview.R
import com.example.recicleview.adapter.ClasseAdapter
import com.example.recicleview.models.Classe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //porque sim, já tava chato toda hora trocar
    private val self = this

    private  lateinit var adapter: ClasseAdapter

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
        self.adapter = ClasseAdapter(self.classlist, self)
        self.recycleView.adapter = self.adapter
        self.recycleView.addItemDecoration(DividerItemDecoration(self, DividerItemDecoration.VERTICAL))
        self.recycleView.layoutManager = LinearLayoutManager(self)
    }

}