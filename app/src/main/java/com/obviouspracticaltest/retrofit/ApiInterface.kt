package retrofit


import com.obviouspracticaltest.models.GalleryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("apod")
    fun getGalleryList(@QueryMap fields: Map<String, String>) : Call<List<GalleryModel>>
  }
