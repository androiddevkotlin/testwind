package com.kalashnyk.denys.windmillweather.usecases.repository.datasource

import androidx.paging.DataSource
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.AppDatabase
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.ForecastEntity
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory

interface ForecastDataSource {
    fun getDataSource(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun insert(item: ForecastEntity)
    fun insertList(items: List<ForecastEntity>)
    fun deleteCache()
}

class ForecastDataSourceImpl(
    private val db: AppDatabase
) :
    ForecastDataSource {

    override fun getDataSource(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        db.forecastDao()
            .getDataSource()
            .mapByPage(factory::convert)

    override fun insert(item: ForecastEntity) {
        db.forecastDao().insert(item)
    }

    override fun insertList(items: List<ForecastEntity>) {
        db.forecastDao().insertList(items)
    }

    override fun deleteCache() {
        db.forecastDao().deleteAll()
    }

}