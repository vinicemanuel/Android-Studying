package com.example.myweather.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.adatper.SearchAdapter
import com.example.myweather.extensions.isTrimEmpty
import com.example.myweather.manager.OpenWeatherManager
import com.example.myweather.model.City
import com.example.myweather.model.Element
import com.example.myweather.model.Root
import com.example.myweather.room.database.AppDatabase
import com.example.myweather.room.model.CityDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    lateinit var searchButton: Button
    lateinit var textSearch: EditText
    lateinit var recycleView: RecyclerView
    lateinit var saveButton: FloatingActionButton

    private val TAG = "SearchFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configFloatingButton(view)
        configRecycleView(view)
        configTextSearch(view)
        configSearButton(view)
    }

    private fun configFloatingButton(view: View) {
        this.saveButton = view.findViewById(R.id.floatingActionButton)
        this.saveButton.setOnClickListener {
            val city = textSearch.text.toString()

            getCityData(city)
        }
    }

    private fun getCityData(city: String) {
        val service = OpenWeatherManager().getOpenWeatherService()
        val call = service.getCityWeather(city)
        call.enqueue(object: Callback<City> {
            override fun onResponse(call: Call<City>, response: Response<City>) {

                if (response.isSuccessful) {

                    response.body()?.let { city ->
                        context?.let { myContext ->
                            val database = AppDatabase.getInstance(myContext)
                            val result = database?.cityDatabaseDAO()?.save(CityDatabase(city.id, city.name))
                            Toast.makeText(myContext, "Result: $result", Toast.LENGTH_LONG).show()
                        }
                    }

                } else {
                    Log.e(TAG, "response is not success")
                }

            }

            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.e(TAG, "response is not success")
            }
        })
    }

    private fun configRecycleView(view: View) {
        this.recycleView = view.findViewById(R.id.search_recycle)
        this.recycleView.adapter = SearchAdapter(mutableListOf(), context)
        this.recycleView.addItemDecoration(SearchAdapter.SearchItemDecoration(30))
    }

    private fun configTextSearch(view: View) {
        this.textSearch = view.findViewById(R.id.text_search)
    }

    private fun configSearButton(view: View) {

        this.searchButton = view.findViewById(R.id.button_search)
        this.searchButton.setOnClickListener {

            if (this.isConnectivityAvailable()) {

                val city = this.textSearch.text.toString()
                if (!city.isTrimEmpty()) {
                    getListData(city)
                }

            } else {
                Toast.makeText(context,getString(R.string.toast_offline), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getListData(city: String) {
        val service = OpenWeatherManager().getOpenWeatherService()
        val call = service.findTmperature(city)
        call.enqueue(object: Callback<Root> {

            override fun onResponse(call: Call<Root>, response: Response<Root>) {

                if (response.isSuccessful) {
                    val root = response.body()
                    if (root is Root) {
                        configSearchListData(root)
                    }
                } else {
                    Log.e(TAG, "response is not success")
                }
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                Log.e(TAG, "response is not success")
            }
        })
    }

    private fun configSearchListData(root: Root) {
        val elements = mutableListOf<Element>()
        root.list.forEach {
            elements.add(it)
        }

        (this.recycleView.adapter as SearchAdapter).configItems(elements)
        this.recycleView.layoutManager = LinearLayoutManager(this.context)
    }

    @SuppressLint("WrongConstant")
    private fun isConnectivityAvailable(): Boolean {
        val manager = (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        var result = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.getNetworkCapabilities(manager.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        } else {
            manager.activeNetworkInfo?.run {
                result = (type == ConnectivityManager.TYPE_WIFI) || (type == ConnectivityManager.TYPE_MOBILE)
            }
        }

        return result
    }
}