package com.example.myweather.adatper

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweather.R
import com.example.myweather.model.Element
import kotlinx.android.synthetic.main.search_cell.view.*

class SearchAdapter(val list: MutableList<Element>?, context: Context?): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_cell, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val element = this.list?.elementAt(position)
        if (element is Element) {
            holder.bind(element)
        }
    }

    override fun getItemCount(): Int {
        return  this.list?.size ?: 0
    }

    fun configItems(elements: MutableList<Element>) {
        list?.clear()
        elements.forEach {
            list?.add(it)
        }
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textCityName = itemView.city_name
        private val textCityCode = itemView.city_code
        private val iconWeather = itemView.weather_icon

        fun bind(element: Element) {
            this.textCityName.text = element.name
            this.textCityCode.text = element.id.toString()

            Glide.with(itemView.context)
                .load("http://openweathermap.org/img/wn/${element.weather[0].icon}@4x.png")
                .error(R.drawable.ic_baseline_error_outline_24)
                .circleCrop()
                .into(iconWeather)

        }
    }

    class SearchItemDecoration(private val height: Int): RecyclerView.ItemDecoration() {
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