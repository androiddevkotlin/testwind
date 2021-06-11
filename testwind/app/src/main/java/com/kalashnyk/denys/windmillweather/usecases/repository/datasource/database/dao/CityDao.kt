package com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM cities ORDER BY currentLocation DESC")
    fun getAll(): List<CityEntity>

    @Query("SELECT * FROM cities WHERE id = :id")
    fun getById(id: Int): CityEntity

    @Query("SELECT * FROM cities WHERE name = :name")
    fun getByName(name: String): CityEntity

    @Query("SELECT * FROM cities ORDER BY currentLocation DESC")
    fun getDataSource(): DataSource.Factory<Int, CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CityEntity)

    @Update
    fun updateList(list: List<CityEntity>)

    @Update
    fun update(item: CityEntity)

    @Query("DELETE FROM cities")
    fun deleteAll()

    @Query("DELETE FROM cities WHERE id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM cities WHERE name = :name")
    fun deleteByName(name: String)
}