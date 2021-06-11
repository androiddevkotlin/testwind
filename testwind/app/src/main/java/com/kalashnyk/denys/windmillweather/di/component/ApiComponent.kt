package com.kalashnyk.denys.windmillweather.di.component

import com.kalashnyk.denys.windmillweather.di.module.ApiModule
import com.kalashnyk.denys.windmillweather.di.scope.ApiScope
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.ForecastRemoteDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.WeatherRemoteDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val communicator: ServerCommunicator
    val forecastRemote: ForecastRemoteDataSource
    val weatherRemote: WeatherRemoteDataSource
}