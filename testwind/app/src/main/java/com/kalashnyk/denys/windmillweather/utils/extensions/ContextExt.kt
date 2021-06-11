package com.kalashnyk.denys.windmillweather.utils.extensions

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.kalashnyk.denys.windmillweather.R
import com.kalashnyk.denys.windmillweather.ui.fragment.cities.binding.AddCityCallback
import com.kalashnyk.denys.windmillweather.utils.textwatcher.CityTextWatcher

fun Context.showAddCityDialog(callback: AddCityCallback): AlertDialog {
    val input = EditText(this).apply {
        setSingleLine()
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ).apply {
            leftMargin = resources.getDimensionPixelSize(R.dimen.indentation_16)
            rightMargin = resources.getDimensionPixelSize(R.dimen.indentation_16)
        }
    }
    val errorView = TextView(this).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ).apply {
            leftMargin = resources.getDimensionPixelSize(R.dimen.indentation_16)
            rightMargin = resources.getDimensionPixelSize(R.dimen.indentation_16)
        }
        gravity = Gravity.CENTER
        text = getString(R.string.city_name_blank)
        setTextColor(ContextCompat.getColor(this@showAddCityDialog, R.color.colorRed))
    }
    val container = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        addView(input)
        addView(errorView)
        input.addTextChangedListener(CityTextWatcher(errorView))
    }
    return AlertDialog.Builder(this)
        .setTitle(R.string.new_dialog_title)
        .setMessage(R.string.new_dialog_message)
        .setView(container)
        .setPositiveButton(R.string.new_dialog_btn_positive) { dialog, _ ->
            input.text.toString().let {
                if (it.isEmpty()) {
                    showToast(getString(R.string.city_name_blank))
                } else {
                    callback.addCity(input.text.toString())
                    dialog.dismiss()
                }
            }
        }
        .setNegativeButton(R.string.new_dialog_btn_negative) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()