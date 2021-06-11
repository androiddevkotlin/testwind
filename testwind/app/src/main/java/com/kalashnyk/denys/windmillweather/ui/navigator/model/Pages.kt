package com.kalashnyk.denys.windmillweather.ui.navigator.model

//todo maybe need sealed class https://kotlinlang.org/docs/reference/sealed-classes.html
enum class Pages(val text: String) {

    CITIES("cities"),
    CITY_DETAIL("city_detail"),
    UNKNOWN("unknown");

    override fun toString(): String {
        return text
    }

    companion object {
        @JvmStatic
        fun fromString(input: String?): Pages {
            return when {
                input == null || input.isEmpty() -> UNKNOWN
                else -> values().firstOrNull {
                    it.text.equals(
                        input,
                        ignoreCase = true
                    )
                } ?: UNKNOWN
            }
        }
    }
}