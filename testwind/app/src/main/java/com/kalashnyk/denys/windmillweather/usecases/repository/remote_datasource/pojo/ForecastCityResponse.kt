package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.ForecastEntity
import com.kalashnyk.denys.windmillweather.utils.settings.DEFAULT_ICON

data class ForecastCityResponse(
    @SerializedName("dt")
    val dt: Long? = 0,
    @SerializedName("main")
    val main: MainResponse? = null,
    @SerializedName("weather")
    val weather: List<WeatherResponse>? = null,
    @SerializedName("clouds")
    val clouds: CloudsResponse? = null,
    @SerializedName("wind")
    val wind: WindResponse? = null,
    @SerializedName("rain")
    val rain: RainResponse? = null,
    @SerializedName("dt_txt")
    val dtTxt: String? = null
) {
    fun convert(cityId: Long) = ForecastEntity(
        dt ?: 0L,
        cityId,
        dtTxt ?: "",
        main?.temp ?: 0.00,
        weather?.get(0)?.icon ?: DEFAULT_ICON
    )
}