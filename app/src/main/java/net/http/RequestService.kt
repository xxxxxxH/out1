package net.http

import model.HotDataBean
import net.model.BaseData
import retrofit2.Call
import retrofit2.http.GET

interface RequestService {

    @GET("appcar/getCategoryThumb.php?page=0&ca_pic_limit=100&ca_limit=100")
    fun getHomeData():Call<HotDataBean>

}