package com.kalashnyk.denys.windmillweather.usecases.repository

import androidx.paging.DataSource
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.CityDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.ForecastDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.ForecastRemoteDataSource
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory
import io.reactivex.Completable

interface ForecastRepository {
    fun fetchForecast(city: String): Completable
    fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun deleteCache(): Completable
}

class ForecastRepositoryImpl(
    private val remote: ForecastRemoteDataSource,
    private val forecastDataSource: ForecastDataSource,
    private val cityDataSource: CityDataSource
): ForecastRepository {

    override fun fetchForecast(city: String): Completable =
        remote.fetchForecast(city)
            .flatMapCompletable { response ->
                Completable.fromAction {
                    response.list.forEach { forecast ->
                        forecastDataSource.insert(forecast.convert(getCityId(city)))
                    }
                }
            }

    override fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        forecastDataSource.getDataSource(factory)

    override fun deleteCache(): Completable = Completable.fromAction {
        forecastDataSource.deleteCache()
    }

    private fun getCityId(city: String): Long = cityDataSource.getItemByName(city).id
}