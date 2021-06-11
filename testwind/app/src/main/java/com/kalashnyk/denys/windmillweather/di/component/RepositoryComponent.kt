package com.kalashnyk.denys.windmillweather.di.component

import com.kalashnyk.denys.windmillweather.di.module.RepositoryModule
import com.kalashnyk.denys.windmillweather.di.scope.RepositoryScope
import com.kalashnyk.denys.windmillweather.usecases.repository.CityRepository
import com.kalashnyk.denys.windmillweather.usecases.repository.ForecastRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val cityRepo: CityRepository
    val forecastRepo: ForecastRepository
}