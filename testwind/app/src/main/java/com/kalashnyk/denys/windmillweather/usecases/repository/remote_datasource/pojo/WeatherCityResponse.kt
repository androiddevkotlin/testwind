package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.utils.settings.DEFAULT_ICON

data class WeatherCityResponse(
    @SerializedName("weather")
    val weather: List<WeatherResponse>,
    @SerializedName("main")
    val main: MainResponse? = null,
    @SerializedName("wind")
    val wind: WindResponse? = null,
    @SerializedName("clouds")
    val clouds: CloudsResponse? = null,
    @SerializedName("dt")
    val date: Long? = 0L,
    @SerializedName("name")
    val name: String
) {
    fun convert(): CityEntity = CityEntity(
        date ?: 0L,
        false,
        name,
        main?.temp ?: 0.0,
        weather[0].icon ?: DEFAULT_ICON,
        wind?.speed ?: 0.0,
        wind?.deg ?: 0.0,
        main?.humidity ?: 0,
        main?.pressure ?: 0
    )

    fun convert(id: Long, isMyLocation: Boolean): CityEntity = CityEntity(
        id,
        isMyLocation,
        name,
        main?.temp ?: 0.0,
        weather[0].icon ?: DEFAULT_ICON,
        wind?.speed ?: 0.0,
        wind?.deg ?: 0.0,
        main?.humidity ?: 0,
        main?.pressure ?: 0
    )

    fun convert(isMyLocation: Boolean): CityEntity = CityEntity(
        1337L,
        isMyLocation,
        name,
        main?.temp ?: 0.0,
        weather[0].icon ?: DEFAULT_ICON,
        wind?.speed ?: 0.0,
        wind?.deg ?: 0.0,
        main?.humidity ?: 0,
        main?.pressure ?: 0
    )
}