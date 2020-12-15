package com.example.myweather.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweather.room.dao.CityDatabaseDAO
import com.example.myweather.room.model.CityDatabase

@Database(entities = [CityDatabase::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDatabaseDAO(): CityDatabaseDAO

    companion object {
        private var sharedInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {

            if (this.sharedInstance == null) {
                synchronized(AppDatabase::class) {
                    this.sharedInstance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MY_WEATHER_DB.db").allowMainThreadQueries().build()
                }
            }

            return this.sharedInstance
        }
    }
}