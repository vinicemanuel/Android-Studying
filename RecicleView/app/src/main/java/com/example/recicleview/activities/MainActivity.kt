package com.example.recicleview.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recicleview.R
import com.example.recicleview.adapter.ClasseAdapter
import com.example.recicleview.adapter.ClasseSelection
import com.example.recicleview.models.Classe
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ClasseSelection, AppCompatActivity() {

    val TAG = "MainActivity"

    //porque sim, já tava chato toda hora trocar

    private  lateinit var adapter: ClasseAdapter

    companion object {
        const val MAIN_ACTIVITY_NEW_CLASSE_REQUEST_CODE = 1
        const val MAIN_ACTIVITY_EDIT_CLASSE_REQUEST_CODE = 2
        const val MAIN_ACTIVITY_RESULT_ID = "CLASS_RESULT"
    }

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

        this.addButton.setOnClickListener {
            this.add()
        }
    }

    private fun add() {
        val myIntent = Intent("CLASSE_DETAIL_NEW_ACTION")

        if (myIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(myIntent, MAIN_ACTIVITY_NEW_CLASSE_REQUEST_CODE)
        } else {
            Toast.makeText(this, "Não foi encontrado uma activity para o " +
                    "intent filter escolhido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerview() {
        this.adapter = ClasseAdapter(this, this.classlist, this)
        this.recycleView.adapter = this.adapter
        this.recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        this.recycleView.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == MAIN_ACTIVITY_NEW_CLASSE_REQUEST_CODE) {
                data?.getParcelableExtra<Classe>(MAIN_ACTIVITY_RESULT_ID)?.let {
                    classlist.add(0,it)
                    adapter.notifyDataSetChanged()
                }

            } else if (requestCode == MAIN_ACTIVITY_EDIT_CLASSE_REQUEST_CODE) {
                data?.getParcelableExtra<Classe>(MAIN_ACTIVITY_RESULT_ID)?.let { classe ->
                    val index = this.classlist.indexOfFirst {filterClasse ->
                        filterClasse.classeID == classe.classeID
                    }

                    if (index != -1) {
                        this.classlist[index] = classe
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val jsonList = Gson().toJson(this.classlist)
        outState.putString("MY_DATA_ARRAY", jsonList)
        adapter.notifyDataSetChanged()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val listJson = savedInstanceState.getString("MY_DATA_ARRAY")?.let {
            val list = Gson().fromJson(it, Array<Classe>::class.java).toMutableList()

            classlist.clear()
            classlist.addAll(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun clickAt(index: Int) {
        val myIntent = Intent("CLASSE_DETAIL_EDIT_ACTION")
        myIntent.putExtra(MAIN_ACTIVITY_RESULT_ID, this.classlist[index])
        startActivityForResult(myIntent, MAIN_ACTIVITY_EDIT_CLASSE_REQUEST_CODE)
    }

}