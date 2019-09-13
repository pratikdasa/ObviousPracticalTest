package com.obviouspracticaltest.viewmodel

import android.app.Activity
import android.os.AsyncTask
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obviouspracticaltest.ObviousPracticalTest
import com.obviouspracticaltest.R
import com.obviouspracticaltest.models.GalleryModel
import com.obviouspracticaltest.utils.SharedPreference
import com.obvioustest.room.GalleryRoomDatabase
import com.obvioustest.utils.StaticUtils
import com.obvioustest.utils.WsParamUtils
import retrofit.WsFactory
import retrofit.WsResponse
import retrofit.WsUtils
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

class NasaPhotosListViewModel(activity: Activity) : ViewModel(), WsResponse {
    var activity: Activity
    var retrofit: Retrofit? = null
        @Inject set
    val galleryModelList: MutableLiveData<List<GalleryModel>> by lazy { MutableLiveData<List<GalleryModel>>() }

    init {
        this.activity = activity
        (activity.application as ObviousPracticalTest).getNetComponent().inject(this)
    }

    override fun successResponse(response: Any?, code: Int) {
        when (code) {
            StaticUtils.REQUEST_FOR_GALLARY -> {
                val galleryModelList: List<GalleryModel> = response as List<GalleryModel>
                this.galleryModelList.value = galleryModelList
                SharedPreference(activity).saveCurrentDate(StaticUtils.getCurrentDate())
                Insert().execute(galleryModelList)
            }
        }
    }

    override fun failureRespons(error: Throwable, code: Int) {
        val builder: AlertDialog.Builder? = activity.let {
            AlertDialog.Builder(it)
        }
        StaticUtils.getAlertMessage(
            activity,
            activity.getString(R.string.error_message),
            activity.getString(R.string.dialog_title)
        )

    }

    override fun noInternetConnection(code: Int) {
        if (galleryModelList.value != null && galleryModelList.value?.size!! > 0) {
            SelectRecord().execute()
        } else {
            StaticUtils.getAlertMessage(
                activity,
                activity.getString(R.string.no_internet_connection),
                activity.getString(R.string.dialog_title)
            )
        }
    }

    fun apiCallForGettingGalleryImage() {
        val repestObj = HashMap<String, String>()
        repestObj.put(WsParamUtils.API_KEY, StaticUtils.API_KEY)
        repestObj.put(WsParamUtils.HD, StaticUtils.IS_HD)
        if (TextUtils.isEmpty(SharedPreference(activity).getCurrenDate())) {
            repestObj.put(WsParamUtils.START_DATE, StaticUtils.getBeforeOneMonthDate())
            repestObj.put(WsParamUtils.END_DATE, StaticUtils.getCurrentDate())
            val galleryResponse = WsFactory.getImageGallary(retrofit, repestObj)
            WsUtils.getReponse(activity, true, galleryResponse as Call<Any>, StaticUtils.REQUEST_FOR_GALLARY, this)
        } else if (StaticUtils.getCurrentDate().equals(SharedPreference(activity).getCurrenDate())) {
            SelectRecord().execute()
        } else {
            val repestObj = HashMap<String, String>()
            repestObj.put(WsParamUtils.DATE, StaticUtils.getCurrentDate())
            val galleryResponse = WsFactory.getImageGallary(retrofit, repestObj)
            WsUtils.getReponse(activity, true, galleryResponse as Call<Any>, StaticUtils.REQUEST_FOR_GALLARY, this)
        }
    }

    inner class Insert : AsyncTask<List<GalleryModel>, Void, Void>() {
        override fun doInBackground(vararg params: List<GalleryModel>): Void? {
            GalleryRoomDatabase.getDatabase(activity).galleryDao().insert(params[0])
            return null
        }
    }


    inner class SelectRecord : AsyncTask<Void, Void, List<GalleryModel>>() {

        override fun doInBackground(vararg p0: Void?): List<GalleryModel> {
            return GalleryRoomDatabase.getDatabase(activity).galleryDao().getGalleryList()
        }

        override fun onPostExecute(result: List<GalleryModel>?) {
            super.onPostExecute(result)
            this@NasaPhotosListViewModel.galleryModelList.value = result
        }
    }

}