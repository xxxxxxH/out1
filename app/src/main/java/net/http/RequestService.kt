package net.http

import model.BannerDataBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestService {

    @GET("api")
    fun getDatas(
        @Query("version") version: String,
        @Query("appid") appid: String,
        @Query("appsecret") appsecret: String
    ): Call<BannerDataBean>

    @GET("appeagle/getGallery.php")
    fun getBannerData():Call<BannerDataBean>
}