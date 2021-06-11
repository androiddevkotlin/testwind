package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName

data class WindResponse(
    @SerializedName("speed")
    val speed: Double? = 0.0,
    @SerializedName("deg")
    val deg: Double? = 0.0
)