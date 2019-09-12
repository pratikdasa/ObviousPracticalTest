package retrofit


import android.app.Activity
import com.kaopiz.kprogresshud.KProgressHUD
import com.obvioustest.utils.StaticUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class WsUtils {
    abstract fun init()
    companion object {
        fun getReponse(
            context: Activity?,
            isShowProgress: Boolean,
            call: Call<Any>,
            code: Int,
            jsonResponse: WsResponse
        ) {
            if (StaticUtils.isNetworkConnected(context!!)) {
                val hud: KProgressHUD = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                if (isShowProgress) {
                    (hud as KProgressHUD).show()
                }
                call.enqueue(object : Callback<Any> {
                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        (hud as KProgressHUD).dismiss()
                        jsonResponse.failureRespons(t, code)
                    }
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        (hud as KProgressHUD).dismiss()
                        jsonResponse.successResponse(response.body(), code)
                    }
                })
              } else {
                jsonResponse.noInternetConnection(code)
            }
         }
     }
}