package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource

import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator.ServerCommunicator
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo.WeatherCityResponse
import com.kalashnyk.denys.windmillweather.utils.rx.RxTransformers
import io.reactivex.Single

interface WeatherRemoteDataSource {
    fun fetchWeather(city: String): Single<WeatherCityResponse>
    fun fetchCurrentCityWeather(point: Pair<Double, Double>): Single<WeatherCityResponse>
}

class WeatherRemoteDataSourceImpl(private val communicator: ServerCommunicator): WeatherRemoteDataSource {

    override fun fetchWeather(city: String): Single<WeatherCityResponse> = communicator
        .fetchWeather(city)
        .compose(RxTransformers().singleTransformer())

    override fun fetchCurrentCityWeather(point: Pair<Double, Double>): Single<WeatherCityResponse> = communicator
        .fetchCurrentCityWeather(point)
        .compose(RxTransformers().singleTransformer())

}