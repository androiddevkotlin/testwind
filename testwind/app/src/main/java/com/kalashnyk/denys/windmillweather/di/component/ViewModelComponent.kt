package com.kalashnyk.denys.windmillweather.di.component

import com.kalashnyk.denys.windmillweather.di.module.ViewModelModule
import com.kalashnyk.denys.windmillweather.di.scope.ViewModelScope
import com.kalashnyk.denys.windmillweather.ui.activity.main.MainActivity
import com.kalashnyk.denys.windmillweather.ui.activity.splash.SplashActivity
import com.kalashnyk.denys.windmillweather.ui.fragment.cities.CitiesFragment
import com.kalashnyk.denys.windmillweather.ui.fragment.detail.CityDetailFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: CitiesFragment)
    fun inject(fragment: CityDetailFragment)
}