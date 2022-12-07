package com.example.basicmaterial3.domain

import android.util.Log
import com.example.basicmaterial3.BuildConfig

/**
 * this function is created to print the log only when the app runs in debug mode
 */
fun printLog(msg: String?, className: Any) {

    if (BuildConfig.DEBUG) {
        Log.d(className::class.java.simpleName + "", "$msg")
    }
}