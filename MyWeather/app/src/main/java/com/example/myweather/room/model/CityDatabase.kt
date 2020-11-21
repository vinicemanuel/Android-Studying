package com.example.myweather.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CityDatabase(@PrimaryKey val id: Long, val cityName: String) {

}