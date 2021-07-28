package net.basicmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        showPosition(0)
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