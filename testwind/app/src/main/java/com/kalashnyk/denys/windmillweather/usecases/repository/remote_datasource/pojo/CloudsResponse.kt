package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo

import com.google.gson.annotations.SerializedName

data class CloudsResponse(
    @SerializedName("all")
    val all: Int? = null
)