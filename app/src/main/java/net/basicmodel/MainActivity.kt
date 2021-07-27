package net.basicmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import model.BannerDataBean
import net.http.RequestService
import net.http.RetrofitUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val retrofit = RetrofitUtils.get().retrofit
    val service = retrofit.create(RequestService::class.java)
    var bannerDataBean: BannerDataBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service.getBannerData().enqueue(object :Callback<BannerDataBean>{
            override fun onResponse(call: Call<BannerDataBean>, response: Response<BannerDataBean>) {
                Log.i("xxxxxxH","response=" + response)
            }

            override fun onFailure(call: Call<BannerDataBean>, t: Throwable) {
                Log.i("xxxxxxH","onFailure")
            }

        })
    }


}