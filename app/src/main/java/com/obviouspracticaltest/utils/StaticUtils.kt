package com.obvioustest.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import com.obviouspracticaltest.R
import java.text.SimpleDateFormat
import java.util.*



object StaticUtils {

    val REQUEST_FOR_GALLARY = 1002
    val API_KEY = "8Hw2ydIhBUvLLRmV53iwxywrla2U1KGxGiBeqYII"
    val IS_HD = "true"

    fun isNetworkConnected(activity: Context): Boolean {
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

    fun getAlertMessage(activity:Activity, message:String, title:String){
        val builder: AlertDialog.Builder? = activity.let {
            AlertDialog.Builder(it)
        }
        builder.apply {
            this!!.setPositiveButton(
                R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->

                })
        }
        builder?.setMessage(message)?.setTitle(title)
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }


}