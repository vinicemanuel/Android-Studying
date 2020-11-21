package com.example.myweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.adatper.FavoritiesAdatper
import com.example.myweather.adatper.SearchAdapter
import com.example.myweather.model.City
import com.example.myweather.room.database.AppDatabase

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment() {

    lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.configRecycleView(view)

        (this.recycleView.adapter as FavoritiesAdatper).configItems(this.getFavorites())
    }

    private fun getFavorites(): List<City> {

        var cityList: List<City> = listOf()

        context?.let { myContext ->
            val database = AppDatabase.getInstance(myContext)
            val list = database?.cityDatabaseDAO()?.getAllCitiesDatabase()
            cityList = list?.map { City(it.id, it.cityName) } ?: listOf()
        }

        return cityList
    }

    private fun configRecycleView(view: View) {
        this.recycleView = view.findViewById(R.id.favorites_recycleview)
        this.recycleView.adapter = FavoritiesAdatper(mutableListOf())
        this.recycleView.layoutManager = LinearLayoutManager(this.context)
        this.recycleView.addItemDecoration(FavoritiesAdatper.FavoritesItemDecoration(30))
    }
}