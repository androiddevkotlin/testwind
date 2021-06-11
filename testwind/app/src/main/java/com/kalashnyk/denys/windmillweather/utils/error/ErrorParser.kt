package com.kalashnyk.denys.windmillweather.utils.error

import android.content.Context
import com.kalashnyk.denys.windmillweather.R
import retrofit2.HttpException

class ErrorParser(private val context: Context) {

    fun parse(t: Throwable): String {
        return when (t) {
            is HttpException -> {
               when (t.code()) {
                   404 -> context.getString(R.string.error_not_found)
                   else -> context.getString(R.string.error_network)
               }
            }
            else -> context.getString(R.string.error_general)
        }
    }

}