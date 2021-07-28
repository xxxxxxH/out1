package net.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.layout_fragment_hot.*
import model.HotDataBean
import net.adapter.ImgAdapter
import net.basicmodel.GalleryActivity
import net.basicmodel.R
import net.http.RequestService
import net.http.RetrofitUtils
import net.interFace.OnItemClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotFragment : Fragment(), OnItemClickListener {

    val retrofit = RetrofitUtils.get().retrofit
    val service = retrofit.create(RequestService::class.java)
    var hotDataBean: HotDataBean? = null
    var adapter: ImgAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_hot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        service.getHomeData().enqueue(object : Callback<HotDataBean> {
            override fun onResponse(call: Call<HotDataBean>, response: Response<HotDataBean>) {
                Log.i("xxxxxxH", "response = " + response)
                hotDataBean = response.body()
                adapter = ImgAdapter(hotDataBean, activity, activity)
                adapter!!.setOnItemClickListener(this@HotFragment)
                recyclerviewHot.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerviewHot.adapter = adapter
            }

            override fun onFailure(call: Call<HotDataBean>, t: Throwable) {
                Log.i("xxxxxxH", "onFailure")
            }
        })
    }

    override fun onItemClick(view: View?, position: Int, flag: String?) {
        val intent = Intent(activity,GalleryActivity::class.java)
        val bundle = Bundle();
        bundle.putSerializable("data", adapter?.data)
        bundle.putInt("position",position)
        intent.putExtra("bundle",bundle)
        activity?.startActivity(intent)
    }
}