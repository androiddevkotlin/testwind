package com.kalashnyk.denys.windmillweather.domain.forecast

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kalashnyk.denys.windmillweather.base.domain.BasePagingViewModel
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.ForecastUseCases
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory
import com.kalashnyk.denys.windmillweather.utils.logger.Logger
import com.kalashnyk.denys.windmillweather.utils.paging.ItemsLoadListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(private val citiesUseCases: ForecastUseCases): BasePagingViewModel() {

    private lateinit var city: CityEntity

    init {
        initPagedConfig()
    }

    fun initLiveData(listener: ItemsLoadListener<PagedList<BaseCardModel>>) {
        itemLoadedListener = listener
        initPagedListLiveData(citiesUseCases.getDataSourceFactory(ConverterFactory()))
    }

    fun getPagedList(): LiveData<PagedList<BaseCardModel>> = listCards

    fun setForecastCity(city: CityEntity) {
        this.city = city
    }

    override fun fetchData() {
        disposable.add(
            citiesUseCases.fetchForecast(city.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.d(this::class, "Forecast fetched!")
                    setLoading(false)
                    setRefreshing(false)
                }, {
                    Logger.e(this::class, it)
                    setLoading(false)
                    setRefreshing(false)
                })
        )
    }

    override fun clearCachedItems() { }
}