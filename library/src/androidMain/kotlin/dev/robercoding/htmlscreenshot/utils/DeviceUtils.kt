package dev.robercoding.htmlscreenshot.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

fun getDeviceWidth(context: Context): Int {
    val displayMetrics = DisplayMetrics()
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}