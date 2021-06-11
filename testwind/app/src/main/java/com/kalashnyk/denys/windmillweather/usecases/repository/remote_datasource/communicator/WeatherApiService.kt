package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator

import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo.ForecastResponse
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo.WeatherCityResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("/data/2.5/forecast")
    fun getForecastByCityName(
        @Query("appid") apiKey: String,
        @Query("q") query: String
    ): Single<ForecastResponse>

    @GET("/data/2.5/weather")
    fun getWeatherByCityName(
        @Query("appid") apiKey: String,
        @Query("q") query: String
    ): Single<WeatherCityResponse>

    @GET("/data/2.5/weather")
    fun getWeatherByPoint(
        @Query("appid") apiKey: String,
        @Query("lat") lat: Double,
        @Query("lon") lng: Double
    ): Single<WeatherCityResponse>
}