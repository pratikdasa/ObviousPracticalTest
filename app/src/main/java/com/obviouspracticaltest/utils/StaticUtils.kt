package com.obvioustest.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*



object StaticUtils {

    val REQUEST_FOR_GALLARY = 1002
    val API_KEY = "8Hw2ydIhBUvLLRmV53iwxywrla2U1KGxGiBeqYII"
    val IS_HD = "true"

    fun isNetworkConnected(activity: Activity): Boolean {
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null
    }

    fun getCurrentDate(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = df.format(c)
        return formattedDate
    }

    fun getBeforeOneMonthDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val date = calendar.time
        val format = SimpleDateFormat("yyyy-MM-dd")
        val dateOutput = format.format(date)
        return dateOutput
    }


}