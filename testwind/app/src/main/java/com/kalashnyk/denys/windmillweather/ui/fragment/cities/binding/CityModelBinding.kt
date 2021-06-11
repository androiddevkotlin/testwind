package com.kalashnyk.denys.windmillweather.ui.fragment.cities.binding

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.windmillweather.R
import com.kalashnyk.denys.windmillweather.usecases.cards.CityCardModel
import com.kalashnyk.denys.windmillweather.utils.extensions.*
import com.kalashnyk.denys.windmillweather.utils.logger.Logger
import com.kalashnyk.denys.windmillweather.utils.settings.FORECAST_INPUT_DATE_TIME_FORMAT
import com.kalashnyk.denys.windmillweather.utils.settings.FORECAST_OUTPUT_DATE_FORMAT
import com.kalashnyk.denys.windmillweather.utils.settings.FORECAST_OUTPUT_TIME_FORMAT

class CityModelBinding(
    private val data: CityCardModel,
    private val listener: CitiesActionListener?
): BaseObservable() {

    val name: String
        @Bindable get() = data.getBaseModel().name

    val temp: String
        @Bindable get() = data.getBaseModel().temperature.toCelsius().format()

    val icon: String
        @Bindable get() = data.getBaseModel().icon

    val location: Boolean
        @Bindable get() = data.getBaseModel().currentLocation

    fun onCityClick(model: CityModelBinding) {
        listener?.showCityDetail(model.data.getBaseModel())
    }

    fun onDeleteClick(model: CityModelBinding) {
        listener?.deleteCity(model.data.getBaseModel())
    }

    companion object {
        @JvmStatic
        @BindingAdapter("icon")
        fun bindIcon(image: ImageView, icon: String) {
            image.load(icon.getIconUrl())
        }

        @JvmStatic
        @BindingAdapter("celsius")
        fun bindCelsius(label: TextView, degrees: String) {
            label.text = label.context.getString(R.string.celsius, degrees)
        }

        @JvmStatic
        @BindingAdapter("celsius")
        fun bindCelsius(label: TextView, degrees: Double) {
            label.text = label.context.getString(R.string.celsius, degrees.toCelsius().format())
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("humidity")
        fun bindHumidity(label: TextView, humidity: Int) {
            label.text = "$humidity%"
        }

        @JvmStatic
        @BindingAdapter("pressure")
        fun bindPressure(label: TextView, pressure: Int) {
            label.text = label.context.getString(R.string.hpa, pressure.toString())
        }

        @JvmStatic
        @BindingAdapter("wind")
        fun bindWind(label: TextView, wind: Double) {
            label.text = label.context.getString(R.string.ms, wind.format())
        }

        @JvmStatic
        @BindingAdapter("date")
        fun bindDate(label: TextView, date: String) {
            label.text = date.formatDateFromDateString(
                FORECAST_INPUT_DATE_TIME_FORMAT, FORECAST_OUTPUT_DATE_FORMAT
            )
        }

        @JvmStatic
        @BindingAdapter("time")
        fun bindTime(label: TextView, time: String) {
            label.text = time.formatDateFromDateString(
                FORECAST_INPUT_DATE_TIME_FORMAT, FORECAST_OUTPUT_TIME_FORMAT
            )
        }

        @JvmStatic
        @BindingAdapter("pinLocation")
        fun bindLocationPin(image: ImageView, isMyLocation: Boolean) {
            image.apply { if (isMyLocation) visible() else gone() }
        }

        @JvmStatic
        @BindingAdapter("delLocation")
        fun bindLocationDeleter(image: ImageView, isMyLocation: Boolean) {
            image.apply { if (isMyLocation) gone() else visible() }
        }
    }
}