package com.kalashnyk.denys.windmillweather.utils.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.getIconUrl(): String = "https://openweathermap.org/img/wn/${this}@2x.png"

@Throws(ParseException::class)
fun String.formatDateFromDateString(
    inputDateFormat: String, outputDateFormat: String
): String? {
    val parsedDate: Date
    val outputDateString: String
    val inputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
    val outputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
    parsedDate = inputDateFormat.parse(this)
    outputDateString = outputDateFormat.format(parsedDate)
    return outputDateString
}