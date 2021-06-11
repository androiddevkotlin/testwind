package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName

class MainResponse (
    @SerializedName("temp")
    val temp: Double? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null
)