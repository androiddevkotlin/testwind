package com.kalashnyk.denys.windmillweather.usecases

import androidx.paging.DataSource
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.ForecastRepository
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory
import io.reactivex.Completable

interface ForecastUseCases {
    fun fetchForecast(city: String): Completable
    fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun deleteCache(): Completable
}

class ForecastUseCasesImpl(private val repo: ForecastRepository): ForecastUseCases {

    override fun fetchForecast(city: String): Completable = repo.fetchForecast(city)

    override fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        repo.getDataSourceFactory(factory)

    override fun deleteCache(): Completable = repo.deleteCache()
}