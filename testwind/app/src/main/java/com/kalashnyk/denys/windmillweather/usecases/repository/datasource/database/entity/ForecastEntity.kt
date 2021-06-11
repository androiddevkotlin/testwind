package com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kalashnyk.denys.windmillweather.base.model.BaseModel

@Entity(tableName = "forecasts")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,

    val cityId: Long,

    val date: String,
    val temperature: Double,
    val icon: String
): BaseModel()