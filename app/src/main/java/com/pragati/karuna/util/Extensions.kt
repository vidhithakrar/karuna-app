package com.pragati.karuna.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView

fun TextView.setLeftDrawable(drawable: Drawable?) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        drawable,
        null,
        null,
        null
    )
}


fun View.enable() {
    alpha = 1f
    this.isEnabled = true
}

fun View.disable() {
    alpha = 0.5f
    this.isEnabled = false
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visibleIf(isVisible: Boolean) {
    return if (isVisible) {
        visible()
    } else {
        gone()
    }
}
