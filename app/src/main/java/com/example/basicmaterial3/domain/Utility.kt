package com.example.basicmaterial3.domain

import android.util.Log
import com.example.basicmaterial3.BuildConfig


fun printLog(msg: String?, className: Any) {

    if (BuildConfig.DEBUG) {
        Log.d(className::class.java.simpleName + "", "$msg")
    }
}