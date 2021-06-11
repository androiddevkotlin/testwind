package com.kalashnyk.denys.windmillweather.utils.error

interface ErrorDelivery {
    fun deliverError(t: Throwable)
}