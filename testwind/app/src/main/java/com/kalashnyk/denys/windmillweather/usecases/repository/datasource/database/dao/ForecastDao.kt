package com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.ForecastEntity

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecasts")
    fun getAll(): List<ForecastEntity>

    @Query("SELECT * FROM forecasts WHERE id = :id")
    fun getById(id: Int): ForecastEntity

    @Query("SELECT * FROM forecasts")
    fun getDataSource(): DataSource.Factory<Int, ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<ForecastEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ForecastEntity)

    @Update
    fun updateList(list: List<ForecastEntity>)

    @Update
    fun update(item: ForecastEntity)

    @Query("DELETE FROM forecasts")
    fun deleteAll()

    @Query("DELETE FROM forecasts WHERE id = :id")
    fun deleteById(id: Int)
}