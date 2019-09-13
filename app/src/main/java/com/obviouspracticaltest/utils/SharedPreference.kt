package com.obviouspracticaltest.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {

    private val PREFS_NAME = "prefrence_data"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val CURRENT_DATE = "current_date"


    fun saveCurrentDate(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(CURRENT_DATE, text)
        editor!!.commit()
    }

    fun getCurrenDate(): String {
        return sharedPref.getString(CURRENT_DATE, "")
    }

}