package com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.dao.CityDao
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.dao.ForecastDao
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.ForecastEntity

@Database(
    entities = [
        CityEntity::class,
        ForecastEntity::class
    ], version = 1
)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun forecastDao(): ForecastDao
}