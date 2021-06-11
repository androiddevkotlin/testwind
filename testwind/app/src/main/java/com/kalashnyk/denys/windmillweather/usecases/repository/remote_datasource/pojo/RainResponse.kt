package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName

class RainResponse (
    @SerializedName("3h")
    val value: String? = null
)