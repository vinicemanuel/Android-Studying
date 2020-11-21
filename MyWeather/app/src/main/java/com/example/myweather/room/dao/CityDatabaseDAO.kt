package com.example.myweather.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweather.room.model.CityDatabase

@Dao
interface CityDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cityDatabase: CityDatabase): Long

    @Query("SELECT * FROM CityDatabase")
    fun getAllCitiesDatabase(): List<CityDatabase>
}