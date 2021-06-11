package com.kalashnyk.denys.windmillweather.utils.paging

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.kalashnyk.denys.windmillweather.R
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.databinding.ItemCityBinding
import com.kalashnyk.denys.windmillweather.databinding.ItemForecastBinding
import com.kalashnyk.denys.windmillweather.ui.fragment.cities.binding.CitiesActionListener
import com.kalashnyk.denys.windmillweather.ui.fragment.cities.binding.CityModelBinding
import com.kalashnyk.denys.windmillweather.ui.fragment.detail.binding.ForecastModelBinding
import com.kalashnyk.denys.windmillweather.usecases.cards.CityCardModel
import com.kalashnyk.denys.windmillweather.usecases.cards.ForecastCardModel
import com.kalashnyk.denys.windmillweather.utils.settings.CARD_CITY
import com.kalashnyk.denys.windmillweather.utils.settings.CARD_FORECAST

class PagingAdapter(diffUtil: DiffUtil.ItemCallback<BaseCardModel>) :
    MultiTypeDataBoundAdapter<BaseCardModel, ViewDataBinding>(diffUtil) {

    private var citiesActionListener: CitiesActionListener? = null

    override fun getItemLayoutId(position: Int): Int {
        getItem(position)?.let {
            return when (it.getCardType()) {
                CARD_CITY -> R.layout.item_city
                CARD_FORECAST -> R.layout.item_forecast
                else -> -1
            }
        }
        return -1
    }

    override fun bindItem(holder: DataBoundViewHolder<ViewDataBinding>, position: Int, payloads: List<Any>) {
        super.bindItem(holder, position, payloads)
        val item = getItem(position)
        when (holder.binding) {
            is ItemCityBinding -> item?.let {
                holder.binding.bindingModel = CityModelBinding(
                    it as CityCardModel,
                    citiesActionListener
                )
            }
            is ItemForecastBinding -> item?.let {
                holder.binding.bindingModel = ForecastModelBinding(
                    it as ForecastCardModel
                )
            }
            else -> { }
        }
    }

    fun setCitiesActionListener(listener: CitiesActionListener) {
        this.citiesActionListener = listener
    }
}