package com.obviouspracticaltest.viewmodel

import android.app.Activity
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obviouspracticaltest.ObviousPracticalTest
import com.obviouspracticaltest.models.GalleryModel
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
                 Insert().execute(galleryModelList)

//                for (i in 0..galleryModelList.size - 1) {
//                    val galleryModel: GalleryModel = galleryModelList.get(i);
//                    Insert().execute(galleryModel)
//                }
            }
        }
    }

    override fun failureRespons(error: Throwable, code: Int) {
        Toast.makeText(activity, "Something went wrong..", Toast.LENGTH_LONG).show()
    }

    override fun noInternetConnection(code: Int) {
        SelectRecord().execute()
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


    inner class Insert : AsyncTask< List<GalleryModel>, Void, Void>() {
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