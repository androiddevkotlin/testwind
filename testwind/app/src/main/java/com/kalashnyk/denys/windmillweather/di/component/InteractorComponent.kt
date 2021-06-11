package com.kalashnyk.denys.windmillweather.di.component

import com.kalashnyk.denys.windmillweather.di.module.InteractorModule
import com.kalashnyk.denys.windmillweather.di.scope.InteractorScope
import com.kalashnyk.denys.windmillweather.usecases.CityUseCases
import com.kalashnyk.denys.windmillweather.usecases.ForecastUseCases
import dagger.Component

@InteractorScope
@Component(modules = [InteractorModule::class], dependencies = [RepositoryComponent::class])
interface InteractorComponent {
    val cityUseCases: CityUseCases
    val forecastUseCases: ForecastUseCases
}