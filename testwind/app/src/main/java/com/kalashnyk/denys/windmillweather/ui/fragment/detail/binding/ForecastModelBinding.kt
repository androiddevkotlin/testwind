package com.kalashnyk.denys.windmillweather.ui.fragment.detail.binding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kalashnyk.denys.windmillweather.usecases.cards.ForecastCardModel
import com.kalashnyk.denys.windmillweather.utils.extensions.format
import com.kalashnyk.denys.windmillweather.utils.extensions.toCelsius

class ForecastModelBinding(private val data: ForecastCardModel): BaseObservable() {

    val icon: String
        @Bindable get() = data.getBaseModel().icon

    val time: String
        @Bindable get() = data.getBaseModel().date

    val temp: String
        @Bindable get() = data.getBaseModel().temperature.toCelsius().format()
}
