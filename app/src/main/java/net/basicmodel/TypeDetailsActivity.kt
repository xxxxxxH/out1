package net.basicmodel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.layout_activity_typedetails.*
import model.HotDataBean
import net.adapter.DetailsAdapter
import net.http.RequestService
import net.http.RetrofitUtils
import net.interFace.OnItemClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeDetailsActivity : AppCompatActivity(), OnItemClickListener {
    val retrofit = RetrofitUtils.get().retrofit
    val service = retrofit.create(RequestService::class.java)
    var adapter: DetailsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_typedetails)
        val intent = intent
        val type = intent.getStringExtra("type") as String
        detailsTitle.text = type
        getData(type)
    }

    fun getData(type: String) {
        service.getTypeDetailsData(type).enqueue(object : Callback<HotDataBean> {
            override fun onResponse(
                call: Call<HotDataBean>,
                response: Response<HotDataBean>
            ) {
                val dataBean = response.body() as HotDataBean
                adapter = DetailsAdapter(dataBean,this@TypeDetailsActivity,this@TypeDetailsActivity)
                val layoutManager = LinearLayoutManager(
                    this@TypeDetailsActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter?.setOnItemClickListener(this@TypeDetailsActivity)
                recyclerviewDetails.layoutManager = layoutManager
                recyclerviewDetails.adapter = adapter
            }

            override fun onFailure(call: Call<HotDataBean>, t: Throwable) {
            }

        })


    }

    override fun onItemClick(view: View?, position: Int, flag: String?) {
        val intent = Intent(this@TypeDetailsActivity, GalleryActivity::class.java)
        val bundle = Bundle();
        bundle.putSerializable("data", adapter?.data)
        bundle.putInt("position", position)
        intent.putExtra("bundle", bundle)
        this@TypeDetailsActivity.startActivity(intent)
    }
}