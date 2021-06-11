package com.kalashnyk.denys.windmillweather.utils.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.kalashnyk.denys.windmillweather.utils.extensions.gone
import com.kalashnyk.denys.windmillweather.utils.extensions.visible

class CityTextWatcher(
    private val errorView: TextView
): TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        if (s.isNullOrBlank()) {
            errorView.visible()
        } else {
            errorView.gone()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}