package com.example.listview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.listview.Model.Estado
import com.example.listview.R
import kotlinx.android.synthetic.main.item_estado.view.*

public class EstadoAdapter(private val context: Context, private val estados: List<Estado>): BaseAdapter() {
    override fun getCount() = estados.size

    override fun getItem(p0: Int) = estados[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, view: View?, viewGroup: ViewGroup?): View {
        val estado = this.estados[p0]

        val holder: ViewHolder
        val linha: View

        if (view == null) {
            linha = LayoutInflater.from(context).inflate(R.layout.item_estado,
                viewGroup, false)
            holder = ViewHolder(linha)
            linha.tag = holder
        } else {
            linha = view
            holder = view.tag as ViewHolder
        }

        holder.txtName.text = estado.nome
        holder.imgBandeira.setImageDrawable(context.resources.obtainTypedArray(R.array.bandeiras).getDrawable(0))
        return linha
    }

    companion object {
        data class ViewHolder(val view: View) {
            val imgBandeira: ImageView = view.imageViewFlag
            val txtName: TextView = view.textViewEstadoNome
        }
    }

}