package com.kalashnyk.denys.windmillweather.di.module

import com.kalashnyk.denys.windmillweather.WindmillWeatherApp
import com.kalashnyk.denys.windmillweather.di.scope.ViewModelScope
import com.kalashnyk.denys.windmillweather.domain.cities.CitiesViewModel
import com.kalashnyk.denys.windmillweather.domain.forecast.ForecastViewModel
import com.kalashnyk.denys.windmillweather.usecases.CityUseCases
import com.kalashnyk.denys.windmillweather.usecases.ForecastUseCases
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(private val app: WindmillWeatherApp) {

    @ViewModelScope
    @Provides
    internal fun provideCitiesViewModel(useCases: CityUseCases): CitiesViewModel =
        CitiesViewModel(
            useCases
        )

    @ViewModelScope
    @Provides
    internal fun provideForecastViewModel(useCases: ForecastUseCases): ForecastViewModel =
        ForecastViewModel(
            useCases
        )
}