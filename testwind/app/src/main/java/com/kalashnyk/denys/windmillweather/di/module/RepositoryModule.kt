package com.kalashnyk.denys.windmillweather.di.module

import com.kalashnyk.denys.windmillweather.di.scope.RepositoryScope
import com.kalashnyk.denys.windmillweather.usecases.repository.CityRepository
import com.kalashnyk.denys.windmillweather.usecases.repository.CityRepositoryImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.ForecastRepository
import com.kalashnyk.denys.windmillweather.usecases.repository.ForecastRepositoryImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.CityDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.ForecastDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.ForecastRemoteDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.WeatherRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    internal fun provideCityRepository(
        remote: WeatherRemoteDataSource,
        cityDataSource: CityDataSource
    ): CityRepository = CityRepositoryImpl(remote, cityDataSource)

    @RepositoryScope
    @Provides
    internal fun provideForecastRepository(
        remote: ForecastRemoteDataSource,
        forecastDs: ForecastDataSource,
        cityDs: CityDataSource
    ): ForecastRepository = ForecastRepositoryImpl(remote, forecastDs, cityDs)
}