package net.basicmodel

import android.os.Bundle
import android.util.Range
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.layout_activity_gallery.*
import model.DataBeanItem
import model.HotDataBean
import net.widget.GalleryItemView
import java.util.ArrayList

/**
 * Copyright (C) 2021,2021/7/28, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class GalleryActivity : AppCompatActivity() {

    var views: ArrayList<View>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_gallery)
        val intent = intent
        val bundle = intent.getBundleExtra("bundle")
        val hotDataBean = bundle?.get("data") as ArrayList<*>
        val position = bundle.get("position") as Int
        for (i in 0 until hotDataBean.size) {
            val itemView = GalleryItemView(this)
            val itemBean = hotDataBean[i] as DataBeanItem
            itemView.setImg(this, itemBean.img_url)
            views?.add(itemView)
        }
        viewpager.adapter = MyPagerAdapter()
        viewpager.currentItem = position
        gallery_tv.text = (position+1).toString() + " / " + hotDataBean.size.toString()
        viewpager.setOnPageChangeListener(object  : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                gallery_tv.text = (position+1).toString() + " / " + hotDataBean.size.toString()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }
    inner class MyPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return views!!.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view==`object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val v = views!![position]
            container.addView(v)
            return v
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val v = views!![position]
            container.removeView(v)
        }
    }
}