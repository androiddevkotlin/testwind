package com.kalashnyk.denys.windmillweather.di.component

import com.kalashnyk.denys.windmillweather.di.module.DatabaseModule
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.CityDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.ForecastDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
    val cityDs: CityDataSource
    val forecastDs: ForecastDataSource
}