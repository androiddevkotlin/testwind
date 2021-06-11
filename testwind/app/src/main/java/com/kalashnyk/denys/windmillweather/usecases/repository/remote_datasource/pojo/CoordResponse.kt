package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName

data class CoordResponse(
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("lon")
    val lon: String? = null
)