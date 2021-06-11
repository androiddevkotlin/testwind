package com.kalashnyk.denys.windmillweather.usecases

import androidx.paging.DataSource
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.CityRepository
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory
import com.kalashnyk.denys.windmillweather.utils.error.ErrorDelivery
import io.reactivex.Completable

interface CityUseCases {
    fun fetchCities(): Completable
    fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun deleteById(id: Long): Completable
    fun deleteByName(name: String): Completable
    fun addCity(name: String, error: ErrorDelivery): Completable
    fun addCurrentCity(point: Pair<Double, Double>): Completable
}

class CityUseCasesImpl(private val repo: CityRepository): CityUseCases {

    override fun fetchCities(): Completable = repo.fetchCities()

    override fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        repo.getDataSourceFactory(factory)

    override fun deleteById(id: Long): Completable = repo.deleteById(id)

    override fun deleteByName(name: String): Completable = repo.deleteByName(name)

    override fun addCity(name: String, error: ErrorDelivery): Completable = repo.addCity(name, error)

    override fun addCurrentCity(point: Pair<Double, Double>): Completable = repo.addCurrentCity(point)

}
