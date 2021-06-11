package com.kalashnyk.denys.windmillweather.di.module

import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.CityDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.CityDataSourceImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.ForecastDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.ForecastDataSourceImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val db: AppDatabase) {

    @Provides
    internal fun provideAppDatabase(): AppDatabase = db

    @Provides
    internal fun provideCityDataSource(db: AppDatabase): CityDataSource =
        CityDataSourceImpl(db)

    @Provides
    internal fun provideForecastDataSource(db: AppDatabase): ForecastDataSource =
        ForecastDataSourceImpl(db)
}