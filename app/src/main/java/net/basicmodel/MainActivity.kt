package net.basicmodel

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.layout_bottom.*
import model.HotDataBean
import net.fragment.CategoritesFragment
import net.fragment.HotFragment
import net.fragment.SettingFragment

class MainActivity : AppCompatActivity() {

    var hotDataBean: HotDataBean? = null

    var hotFragment: HotFragment? = null
    var categoritesFragment: CategoritesFragment? = null
    var settingFragment: SettingFragment? = null

    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
        initView()
        showPosition(0)
    }

    fun requestPermissions() {
        if (checkPermission(permissions[0]) && checkPermission(permissions[1])) {

        } else {
            ActivityCompat.requestPermissions(this, permissions, 321)
        }
    }

    private fun checkPermission(per: String): Boolean {
        return ContextCompat.checkSelfPermission(this, per) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                } else {
                    Log.i("xxxxxxH", "获取权限成功")
                }
            }
        }
    }

    fun initView() {
        HotTv.setOnClickListener {
            showPosition(0)
        }
        CatTv.setOnClickListener {
            showPosition(1)
        }
        SetTv.setOnClickListener {
            showPosition(2)
        }
    }

    fun showPosition(position: Int) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        hideAll(ft)
        if (position == 0) {
            hotFragment = fm.findFragmentByTag("hot") as HotFragment?
            if (hotFragment == null) {
                hotFragment = HotFragment()
                ft.add(R.id.content, hotFragment!!, "hot")
            } else {
                ft.show(hotFragment!!)
            }
        }

        if (position == 1) {
            categoritesFragment = fm.findFragmentByTag("cat") as CategoritesFragment?
            if (categoritesFragment == null) {
                categoritesFragment = CategoritesFragment()
                ft.add(R.id.content, categoritesFragment!!, "cat")
            } else {
                ft.show(categoritesFragment!!)
            }
        }

        if (position == 2) {
            settingFragment = fm.findFragmentByTag("set") as SettingFragment?
            if (settingFragment == null) {
                settingFragment = SettingFragment()
                ft.add(R.id.content, settingFragment!!, "set")
            } else {
                ft.show(settingFragment!!)
            }
        }
        ft.commit()
    }

    fun hideAll(ft: FragmentTransaction) {
        if (hotFragment != null) {
            ft.hide(hotFragment!!)
        }
        if (categoritesFragment != null) {
            ft.hide(categoritesFragment!!)
        }
        if (settingFragment != null) {
            ft.hide(settingFragment!!)
        }
    }
}