package com.example.recicleview.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recicleview.R
import com.example.recicleview.adapter.ClasseAdapter
import com.example.recicleview.models.Classe
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Console

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    //porque sim, já tava chato toda hora trocar
    private val self = this

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
            self.add()
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
        self.adapter = ClasseAdapter(self.classlist, self)
        self.recycleView.adapter = self.adapter
        self.recycleView.addItemDecoration(DividerItemDecoration(self, DividerItemDecoration.VERTICAL))
        self.recycleView.layoutManager = LinearLayoutManager(self)
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

            }
        }
    }

}