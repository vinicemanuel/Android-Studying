package com.example.myweather.adatper

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.model.City
import kotlinx.android.synthetic.main.favority_cell.view.*

interface DeleteCityDelegate{
    fun delete(city: City)
}

class FavoritesAdapter(private val list: MutableList<City>?, private val deleteDelegate: DeleteCityDelegate?): RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesAdapter.FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favority_cell, parent, false),
                deleteDelegate
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val element = this.list?.elementAt(position)
        if (element is City) {
            holder.bind(element)
        }
    }

    override fun getItemCount(): Int {
        return  this.list?.size ?: 0
    }

    fun configItems(favorites: List<City>) {
        list?.clear()
        favorites.forEach {
            list?.add(it)
        }
        notifyDataSetChanged()
    }

    fun delete(city: City) {
        this.list?.remove(city)
        this.notifyDataSetChanged()
    }

    class FavoritesViewHolder(itemView: View, private val deleteDelegate: DeleteCityDelegate?): RecyclerView.ViewHolder(itemView) {

        private val textCityName = itemView.textview_favority_city_name
        private val textCityCode = itemView.textview_favority_city_id
        private val deleteButton = itemView.delete_button

        fun bind(city: City) {
            this.textCityCode.text = city.id.toString()
            this.textCityName.text = city.name
            this.deleteButton.setOnClickListener {
                deleteDelegate?.delete(city)

            }
        }
    }

    class FavoritesItemDecoration(private val height: Int): RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {

            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = height
            }

            outRect.left = height
            outRect.right = height
            outRect.bottom = height
        }
    }
}