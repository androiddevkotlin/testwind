package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName

class WeatherResponse (
    @SerializedName("main")
    val main: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String? = null
)