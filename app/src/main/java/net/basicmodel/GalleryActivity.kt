package net.basicmodel

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.layout_activity_gallery.*
import model.DataBeanItem
import net.widget.GalleryItemView
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Copyright (C) 2021,2021/7/28, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class GalleryActivity : AppCompatActivity() {

    var views: ArrayList<View>? = ArrayList()

    @SuppressLint("ResourceType")
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
        gallery_tv.text = (position + 1).toString() + " / " + hotDataBean.size.toString()
        viewpager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                gallery_tv.text = (position + 1).toString() + " / " + hotDataBean.size.toString()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        gallery_set_wallpaper.setOnClickListener {
            val paperManager = WallpaperManager.getInstance(this@GalleryActivity)

        }
        gallery_save.setOnClickListener {
            downImg(this,(hotDataBean[viewpager.currentItem] as DataBeanItem).img_url,false)
        }

    }

    fun downImg(context: Context,url :String,setWallPaper: Boolean){
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    if (setWallPaper){
                        //TO DO
                    }else{
                        saveImage(resource)
                    }
                }
            })

    }


    fun saveImage(image: Bitmap) {
        var saveImagePath: String? = null
        val imageFileName: String = System.currentTimeMillis().toString() + ".jpg"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "test"
        )

        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            saveImagePath = imageFile.absolutePath
            try {
                val fout = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fout)
                fout.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            galleryAddPic(saveImagePath)
            Toast.makeText(this, "IMAGE SAVED", Toast.LENGTH_LONG).show()
        }
    }

    fun galleryAddPic(imagePath: String) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(imagePath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        sendBroadcast(mediaScanIntent)
    }

    inner class MyPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return views!!.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
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