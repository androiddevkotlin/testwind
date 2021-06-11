package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.ForecastEntity

data class ForecastResponse(
    @SerializedName("cod")
    val code: Int? = 0,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("cnt")
    val count: Int? = 0,
    @SerializedName("list")
    val list: List<ForecastCityResponse>,
    @SerializedName("city")
    val city: CityResponse
)