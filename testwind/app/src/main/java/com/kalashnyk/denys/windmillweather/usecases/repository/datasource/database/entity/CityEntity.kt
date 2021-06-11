package com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kalashnyk.denys.windmillweather.base.model.BaseModel

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,

    val currentLocation: Boolean,

    val name: String,

    val temperature: Double,
    val icon: String,

    val windSpeed: Double,
    val windDegree: Double,

    val humidity: Int,
    val pressure: Int
): BaseModel()