package retrofit


import retrofit2.Call
import retrofit2.Retrofit
 object WsFactory {
     fun getImageGallary(retrofitInstance: Retrofit?, map: Map<String, String>): Call<*> {
        val apiService = retrofitInstance!!.create(ApiInterface::class.java)
        return apiService.getGalleryList(map)
      }
}
