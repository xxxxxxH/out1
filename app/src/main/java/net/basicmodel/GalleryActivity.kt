package net.basicmodel

import android.R.attr
import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.facebook.appevents.ml.Utils
import kotlinx.android.synthetic.main.layout_activity_gallery.*
import model.DataBeanItem
import net.utils.utils
import net.widget.GalleryItemView
import net.widget.LoadingDialog
import java.io.File
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
    var loadingDialog : LoadingDialog? = null

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
            if (loadingDialog == null){
                loadingDialog = LoadingDialog(this@GalleryActivity)
            }
            loadingDialog!!.show()
            Thread(Runnable {
                Thread.sleep(1500)
                downImg(this, (hotDataBean[viewpager.currentItem] as DataBeanItem).img_url, true)
            }).start()
        }
        gallery_save.setOnClickListener {
            if (loadingDialog == null){
                loadingDialog = LoadingDialog(this@GalleryActivity)
            }
            loadingDialog!!.show()
            Thread(Runnable {
                Thread.sleep(1500)
                downImg(this, (hotDataBean[viewpager.currentItem] as DataBeanItem).img_url, false)
            }).start()

        }

    }

    fun downImg(context: Context, url: String, setWallPaper: Boolean) {
        Glide.with(context)
            .downloadOnly()
            .load(url)
            .listener(object : RequestListener<File> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<File>?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (loadingDialog != null && loadingDialog!!.isShowing){
                        loadingDialog!!.dismiss()
                    }
                    Toast.makeText(context, "download failed", Toast.LENGTH_SHORT).show()
                    return false
                }

                override fun onResourceReady(
                    resource: File?,
                    model: Any?,
                    target: Target<File>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        saveToAlbum(context, resource,setWallPaper)
                    }
                    return false
                }

            }).preload();
    }

    /**
     * 保存到相册中
     * @param context 上下文
     * @param srcPath 网络图保存到本地的缓存文件路径
     */
    fun saveToAlbum(context: Context, srcPath: File,setWallPaper: Boolean) {
        val dcimPath = Environment.getExternalStorageDirectory()
        val file = File(dcimPath, System.currentTimeMillis().toString() + ".png");
        val isCopySuccess = utils.copyFile(srcPath, file.getAbsolutePath());
        if (isCopySuccess) {
            //发送广播通知
            context.sendBroadcast(
                Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + file.getAbsolutePath())
                )
            );
            if (setWallPaper){
                val paperManager = WallpaperManager.getInstance(this@GalleryActivity)
                val bitmap = BitmapFactory.decodeFile(file.toString())
                paperManager.setBitmap(bitmap)
                bitmap.recycle()
            }
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
        }
        if (loadingDialog != null && loadingDialog!!.isShowing){
            loadingDialog!!.dismiss()
        }
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