package com.kalashnyk.denys.windmillweather.usecases.repository.datasource

import androidx.paging.DataSource
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.AppDatabase
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.utils.converter.ConverterFactory

interface CityDataSource {
    fun getDataSource(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun getAll(): List<CityEntity>
    fun getItemByName(city: String): CityEntity
    fun insert(item: CityEntity)
    fun deleteById(id: Long)
    fun deleteByName(name: String)
}

class CityDataSourceImpl(private val db: AppDatabase): CityDataSource {

    override fun getDataSource(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        db.cityDao()
            .getDataSource()
            .mapByPage(factory::convert)

    override fun getAll(): List<CityEntity> = db.cityDao().getAll()

    override fun getItemByName(city: String): CityEntity = db.cityDao().getByName(city)

    override fun insert(item: CityEntity) {
        db.cityDao().insert(item)
    }

    override fun deleteById(id: Long) {
        db.cityDao().deleteById(id)
    }

    override fun deleteByName(name: String) {
        db.cityDao().deleteByName(name)
    }

}