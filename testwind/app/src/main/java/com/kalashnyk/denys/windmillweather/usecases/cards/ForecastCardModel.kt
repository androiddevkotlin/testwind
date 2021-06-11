package com.kalashnyk.denys.windmillweather.usecases.cards

import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.ForecastEntity
import com.kalashnyk.denys.windmillweather.utils.settings.CARD_FORECAST

class ForecastCardModel(private var forecast: ForecastEntity): BaseCardModel() {

    override fun getCardId(): String = forecast.id.toString()

    override fun getCardType(): String = CARD_FORECAST

    override fun getBaseModel(): ForecastEntity = forecast

}