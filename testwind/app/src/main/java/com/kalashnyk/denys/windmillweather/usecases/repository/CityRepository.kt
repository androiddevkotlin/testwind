package com.kalashnyk.denys.windmillweather.usecases.repository

import androidx.paging.DataSource
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.CityDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.WeatherRemoteDataSource
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory
import com.kalashnyk.denys.windmillweather.utils.error.ErrorDelivery
import com.kalashnyk.denys.windmillweather.utils.logger.Logger
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

interface CityRepository {
    fun fetchCities(): Completable
    fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun deleteById(id: Long): Completable
    fun deleteByName(name: String): Completable
    fun addCity(name: String, error: ErrorDelivery): Completable
    fun addCurrentCity(point: Pair<Double, Double>): Completable
}

class CityRepositoryImpl(
    private val remote: WeatherRemoteDataSource,
    private val cityDataSource: CityDataSource
): CityRepository {

    override fun fetchCities(): Completable = Completable.fromAction {
        cityDataSource.getAll().forEach {
            val tmpId = it.id
            val tmpLocation = it.currentLocation
            remote.fetchWeather(it.name)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    cityDataSource.insert(it.convert(tmpId, tmpLocation))
                }, {
                    Logger.e(this::class, it)
                })
        }
    }

    override fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        cityDataSource.getDataSource(factory)

    override fun deleteById(id: Long) = Completable.fromAction {
        cityDataSource.deleteById(id)
    }

    override fun deleteByName(name: String) = Completable.fromAction {
        cityDataSource.deleteByName(name)
    }

    override fun addCity(name: String, error: ErrorDelivery): Completable = Completable.fromAction {
        remote.fetchWeather(name)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                cityDataSource.insert(it.convert())
            }, {
                error.deliverError(it)
            })
    }

    override fun addCurrentCity(point: Pair<Double, Double>): Completable = Completable.fromAction {
        remote.fetchCurrentCityWeather(point)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                cityDataSource.insert(it.convert(true))
            }, {
                Logger.e(this::class, it)
            })
    }
}