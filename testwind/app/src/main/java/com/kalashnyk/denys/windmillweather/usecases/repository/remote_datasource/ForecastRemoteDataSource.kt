package com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource

import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator.ServerCommunicator
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.pojo.ForecastResponse
import com.kalashnyk.denys.windmillweather.utils.rx.RxTransformers
import io.reactivex.Single

interface ForecastRemoteDataSource {
    fun fetchForecast(city: String): Single<ForecastResponse>
}

class ForecastRemoteDataSourceImpl(
    private val communicator: ServerCommunicator
): ForecastRemoteDataSource {

    override fun fetchForecast(city: String): Single<ForecastResponse> = communicator
        .fetchForecast(city)
        .compose(RxTransformers().singleTransformer())

}