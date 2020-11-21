package com.example.myweather.adatper

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.model.City
import com.example.myweather.model.Element
import kotlinx.android.synthetic.main.favority_cell.view.*
import kotlinx.android.synthetic.main.search_cell.view.*

class FavoritiesAdatper(private val list: MutableList<City>?): RecyclerView.Adapter<FavoritiesAdatper.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritiesAdatper.FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favority_cell, parent, false)
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

    class FavoritesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textCityName = itemView.textview_favority_city_name
        private val textCityCode = itemView.textview_favority_city_id

        fun bind(city: City) {
            this.textCityCode.text = city.id.toString()
            this.textCityName.text = city.name
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