package com.kalashnyk.denys.windmillweather.di.module

import com.kalashnyk.denys.windmillweather.di.scope.InteractorScope
import com.kalashnyk.denys.windmillweather.usecases.CityUseCases
import com.kalashnyk.denys.windmillweather.usecases.CityUseCasesImpl
import com.kalashnyk.denys.windmillweather.usecases.ForecastUseCases
import com.kalashnyk.denys.windmillweather.usecases.ForecastUseCasesImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.CityRepository
import com.kalashnyk.denys.windmillweather.usecases.repository.ForecastRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @InteractorScope
    @Provides
    internal fun provideCityUseCases(repo: CityRepository): CityUseCases =
        CityUseCasesImpl(repo)

    @InteractorScope
    @Provides
    internal fun provideForecastUseCases(repo: ForecastRepository): ForecastUseCases =
        ForecastUseCasesImpl(repo)
}