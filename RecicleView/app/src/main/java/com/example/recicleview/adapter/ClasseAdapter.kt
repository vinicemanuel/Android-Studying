package com.example.recicleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recicleview.R
import com.example.recicleview.models.Classe
import kotlinx.android.synthetic.main.classe_view.view.*
import java.io.Console

class ClasseAdapter(private val classes: List<Classe>,
                    private val context: Context): RecyclerView.Adapter<ClasseAdapter.ClasseViewHolder>() {

    private val self = this

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasseViewHolder {
        val view = LayoutInflater.from(this.context).inflate(R.layout.classe_view, parent, false)
        val viewHolder = ClasseViewHolder(view)

        return  viewHolder
    }

    override fun onBindViewHolder(holder: ClasseViewHolder, position: Int) {
        val classe = self.classes.elementAt(position)
        holder.name.text = classe.name
        holder.year.text = classe.ano.toString()
    }

    override fun getItemCount() = classes.count()

    class ClasseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val year: TextView = itemView.year
    }
}