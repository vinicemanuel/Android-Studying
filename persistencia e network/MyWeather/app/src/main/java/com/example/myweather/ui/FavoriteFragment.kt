package com.example.myweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.adatper.DeleteCityDelegate
import com.example.myweather.adatper.FavoritesAdapter
import com.example.myweather.model.City
import com.example.myweather.room.database.AppDatabase
import com.example.myweather.room.model.CityDatabase

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment(), DeleteCityDelegate {

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
        this.loadRecycleViewData(this.getFavorites())
    }

    private fun loadRecycleViewData(list: List<City>) {
        (this.recycleView.adapter as FavoritesAdapter).configItems(list)
    }

    override fun delete(city: City) {
        val cityDatabase = CityDatabase(city.id, city.name)
        this.getDatabase()?.cityDatabaseDAO()?.delete(cityDatabase)
        (this.recycleView.adapter as FavoritesAdapter).delete(city)
    }

    private fun getDatabase(): AppDatabase? {
        return context?.let { myContext ->
            AppDatabase.getInstance(myContext)
        }
    }

    private fun getFavorites(): List<City> {
        val list = this.getDatabase()?.cityDatabaseDAO()?.getAllCitiesDatabase()
        return list?.map { City(it.id, it.cityName) } ?: listOf()
    }

    private fun configRecycleView(view: View) {
        this.recycleView = view.findViewById(R.id.favorites_recycleview)
        this.recycleView.adapter = FavoritesAdapter(mutableListOf(),this)
        this.recycleView.layoutManager = LinearLayoutManager(this.context)
        this.recycleView.addItemDecoration(FavoritesAdapter.FavoritesItemDecoration(30))
    }
}