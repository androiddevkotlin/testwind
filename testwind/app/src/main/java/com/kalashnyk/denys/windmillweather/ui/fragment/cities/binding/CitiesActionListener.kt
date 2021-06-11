package com.kalashnyk.denys.windmillweather.ui.fragment.cities.binding

import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity

interface CitiesActionListener {
    fun showCityDetail(city: CityEntity)
    fun deleteCity(city: CityEntity)
}