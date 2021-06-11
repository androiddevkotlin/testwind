package com.kalashnyk.denys.windmillweather.utils.converter

import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.base.model.BaseModel
import com.kalashnyk.denys.windmillweather.usecases.cards.CityCardModel
import com.kalashnyk.denys.windmillweather.usecases.cards.ForecastCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.ForecastEntity
import java.util.ArrayList

class ConverterFactory {

    fun convert(list: List<BaseModel>): List<BaseCardModel> {
        val items = ArrayList<BaseCardModel>()
        list.forEach {
            convert(it)?.apply { items.add(this) }
        }
        return items
    }

    private fun convert(item: BaseModel): BaseCardModel? = when (item) {
        is CityEntity -> CityCardModel(item)
        is ForecastEntity -> ForecastCardModel(item)
        else -> null
    }

}
