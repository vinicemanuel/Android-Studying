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
import android.widget.Toast
import com.example.myweather.R
import com.example.myweather.manager.OpenWeatherManager
import com.example.myweather.model.City
import com.example.myweather.service.OpenWeatherService
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

        configSearButton(view)
    }

    private fun configSearButton(view: View) {

        this.searchButton = view.findViewById(R.id.button_search)
        this.searchButton.setOnClickListener {

            if (this.isConectivityAvaliable()) {
                Toast.makeText(context,getString(R.string.toast_online), Toast.LENGTH_LONG).show()

                val service = OpenWeatherManager().getOpenWeatherService()
                val call = service.getCityWeather("recife")
                call.enqueue(object: Callback<City> {

                    override fun onResponse(call: Call<City>, response: Response<City>) {

                        if (response.isSuccessful) {
                            val city = response.body()
                            Log.e(TAG, "response city is $city")
                        } else {
                            Log.e(TAG, "response city is not success")
                        }
                    }

                    override fun onFailure(call: Call<City>, t: Throwable) {
                        Log.e(TAG, "response is not success")
                    }
                })

            } else {
                Toast.makeText(context,getString(R.string.toast_offline), Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun isConectivityAvaliable(): Boolean {
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