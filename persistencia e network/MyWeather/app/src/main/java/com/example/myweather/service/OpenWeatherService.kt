package com.example.myweather.service

import com.example.myweather.model.City
import com.example.myweather.model.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    //http://api.openweathermap.org/data/2.5/weather?q=Recife&APPID=660bad2b0a02fab5c3e6e02dab9a91b6

    @GET(value = "weather")
    fun getCityWeather(
        @Query("q") cityName: String,
        @Query("APPID") appId: String = "660bad2b0a02fab5c3e6e02dab9a91b6"
    ): Call<City>

    @GET("find")
    fun findTmperature(
        @Query("q") cityName: String,
        @Query("units") units: String = "metrics",
        @Query("APPID") appId: String = "660bad2b0a02fab5c3e6e02dab9a91b6"
    ): Call<Root>
}