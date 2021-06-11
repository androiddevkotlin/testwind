package com.kalashnyk.denys.windmillweather.domain.cities

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kalashnyk.denys.windmillweather.base.domain.BasePagingViewModel
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.CityUseCases
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory
import com.kalashnyk.denys.windmillweather.utils.error.ErrorDelivery
import com.kalashnyk.denys.windmillweather.utils.logger.Logger
import com.kalashnyk.denys.windmillweather.utils.paging.ItemsLoadListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CitiesViewModel(private val citiesUseCases: CityUseCases): BasePagingViewModel(), ErrorDelivery {

    init {
        initPagedConfig()
    }

    fun initLiveData(listener: ItemsLoadListener<PagedList<BaseCardModel>>) {
        itemLoadedListener = listener
        initPagedListLiveData(citiesUseCases.getDataSourceFactory(ConverterFactory()))
    }

    fun getPagedList(): LiveData<PagedList<BaseCardModel>> = listCards

    fun addCity(name: String) {
        disposable.add(
            citiesUseCases.addCity(name, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.d(this::class, "Added new city: $name")
                    setRefreshing(false)
                    setLoading(false)
                }, {
                    deliverError(it)
                })
        )
    }

    fun addCurrentCity(point: Pair<Double, Double>) {
        disposable.add(
            citiesUseCases.addCurrentCity(point)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.d(this::class, "Added current city")
                    setRefreshing(false)
                    setLoading(false)
                }, {
                    deliverError(it)
                })
        )
    }

    fun deleteCity(id: Long) {
        disposable.add(
            citiesUseCases.deleteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.d(this::class, "Deleted city with id: $id")
                    setRefreshing(false)
                    setLoading(false)
                }, {
                    deliverError(it)
                })
        )
    }

    override fun fetchData() {
        disposable.add(
            citiesUseCases.fetchCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.d(this::class, "Weather updated!")
                    setRefreshing(false)
                    setLoading(false)
                }, {
                    deliverError(it)
                })
        )
    }

    override fun clearCachedItems() { }

    override fun deliverError(t: Throwable) {
        setRefreshing(false)
        setLoading(false)
        handleError(t)
    }

}