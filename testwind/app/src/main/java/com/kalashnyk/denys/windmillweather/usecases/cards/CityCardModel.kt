package com.kalashnyk.denys.windmillweather.usecases.cards

import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.utils.settings.CARD_CITY

class CityCardModel(private var city: CityEntity) : BaseCardModel() {

    override fun getCardId(): String = city.id.toString()

    override fun getCardType(): String = CARD_CITY

    override fun getBaseModel(): CityEntity = city

}