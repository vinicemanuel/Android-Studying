package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.example.listview.Adapter.EstadoAdapter
import com.example.listview.Model.Estado
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listEstados = mutableListOf(
        Estado("Paraíba", 0),
        Estado("Pernambuco", 0),
        Estado("Rio Grande do Norte", 0)
    )

    private val mEstadoAdapter by lazy { EstadoAdapter(this, listEstados) }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listView.setOnItemClickListener { parent, view, position, id ->
            val (nome, bandeira) = listEstados[position]
            Toast.makeText(this, "click: $$nome $bandeira", Toast.LENGTH_SHORT).show()
        }

        listView.adapter = mEstadoAdapter

        buttonInserir.setOnClickListener {
            val name = editTextPersonName.text.toString()
            if (isNameValid(name)) {
                listEstados.add(Estado(name, 0))
                mEstadoAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun isNameValid(name: String): Boolean = !name.isNullOrEmpty()
}