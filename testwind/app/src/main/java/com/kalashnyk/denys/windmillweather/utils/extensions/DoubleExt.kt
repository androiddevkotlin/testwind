package com.kalashnyk.denys.windmillweather.utils.extensions

import com.kalashnyk.denys.windmillweather.utils.settings.KELVIN_ABSOLUTE_ZERO
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.toCelsius(): Double = this - KELVIN_ABSOLUTE_ZERO

fun Double.format(): String = BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN).toString()