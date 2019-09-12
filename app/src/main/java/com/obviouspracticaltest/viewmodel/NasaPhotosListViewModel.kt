package com.obviouspracticaltest.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obviouspracticaltest.ObviousPracticalTest
import com.obviouspracticaltest.models.GalleryModel
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
            }
        }
    }

    override fun failureRespons(error: Throwable, code: Int) {
        Toast.makeText(activity, "Something went wrong..", Toast.LENGTH_LONG).show()
    }

    override fun noInternetConnection(code: Int) {
        Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_LONG).show()
    }

    fun apiCallForGettingGalleryImage() {
        val repestObj = HashMap<String, String>()
        repestObj.put(WsParamUtils.API_KEY, StaticUtils.API_KEY)
        repestObj.put(WsParamUtils.START_DATE, StaticUtils.getBeforeOneMonthDate())
        repestObj.put(WsParamUtils.HD, StaticUtils.IS_HD)
        repestObj.put(WsParamUtils.END_DATE, StaticUtils.getCurrentDate())
        val galleryResponse = WsFactory.getImageGallary(retrofit, repestObj)
        WsUtils.getReponse(activity, true, galleryResponse as Call<Any>, StaticUtils.REQUEST_FOR_GALLARY, this)
      }





}