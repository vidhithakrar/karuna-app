package com.pragati.karuna.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager

object KeyboardUtil {
    fun hideKeyboard(context: Activity) {
        val inputMethodManager: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            context.currentFocus?.windowToken, 0
        )
    }
}