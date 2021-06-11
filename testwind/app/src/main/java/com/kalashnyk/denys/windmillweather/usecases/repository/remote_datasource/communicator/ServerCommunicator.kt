package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator

import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo.ForecastResponse
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo.WeatherCityResponse
import com.kalashnyk.denys.windmillweather.utils.logger.Logger
import com.kalashnyk.denys.windmillweather.utils.settings.WEATHER_API_KEY
import io.reactivex.Single

interface ServerCommunicator {
    fun fetchForecast(city: String): Single<ForecastResponse>
    fun fetchWeather(city: String): Single<WeatherCityResponse>
    fun fetchCurrentCityWeather(point: Pair<Double, Double>): Single<WeatherCityResponse>
}

class ServerCommunicatorImpl(
    private val weatherApi: WeatherApiService
) : ServerCommunicator {

    override fun fetchForecast(city: String): Single<ForecastResponse> = weatherApi
        .getForecastByCityName(WEATHER_API_KEY, city)
        .doOnError { Logger.e(this::class, it) }

    override fun fetchWeather(city: String): Single<WeatherCityResponse> = weatherApi
        .getWeatherByCityName(WEATHER_API_KEY, city)
        .doOnError { Logger.e(this::class, it) }

    override fun fetchCurrentCityWeather(point: Pair<Double, Double>): Single<WeatherCityResponse> = weatherApi
        .getWeatherByPoint(WEATHER_API_KEY, point.first, point.second)
        .doOnError { Logger.e(this::class, it) }
}