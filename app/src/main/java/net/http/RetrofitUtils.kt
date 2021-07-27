package net.http

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtils {
    companion object {
        private var instance: RetrofitUtils? = null
            get() {
                if (field == null) {
                    field = RetrofitUtils()
                }
                return field
            }

        @Synchronized
        fun get(): RetrofitUtils {
            return instance!!
        }
    }


    val retrofit = Retrofit.Builder()
        .baseUrl(NetConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}